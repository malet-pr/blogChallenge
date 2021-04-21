package org.alkemy.java.individual.challenge.main.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.validation.Valid;
import org.alkemy.java.individual.challenge.main.model.Post;
import org.alkemy.java.individual.challenge.main.service.PostService;
import org.alkemy.java.individual.challenge.main.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("post")
public class PostController  {
	
	@Autowired
	private PostService postService;
	@Autowired
	private UploadFileService uploadFileService;

    @GetMapping({"/","/home","/index","/posts"})
    public String getPosts(Model model) {
    	List<Post> posts = postService.getAll();
        model.addAttribute("posts", posts);
        return "home";
    }
    
	@GetMapping({"/posts/{id}"})
	public String viewPostDetails(@PathVariable Long id, Model model) { 
		Post post = postService.getById(id);
		if(post==null) {
			return "error-500";
		}
		model.addAttribute("post", post);
		return "post-view";
	}
	
    @GetMapping("/addPost")
    public String addPostForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "add-post-form";
    }

	@PostMapping(value="/posts")
    public String addCourse(@Valid @ModelAttribute Post post, BindingResult bindingResult, @RequestParam("file") MultipartFile image, SessionStatus status) {	 
		if(bindingResult.hasErrors()) {	    
			return "add-post-form";
		}
		if(!image.isEmpty()) {
			if(post.getId() != null && post.getId() > 0 && post.getImage() != null && post.getImage().length() > 0) {
				uploadFileService.delete(post.getImage());
			}
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
			post.setImage(uniqueFilename);
		}
		postService.save(post);
		status.isComplete();
        return "redirect:/home";   
	}
	
    @RequestMapping(path="/deletePost/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deletePost(@PathVariable Long id) {
        if (id >= 0) {
            Post post = postService.getById(id);
            if(post != null) {
                postService.delete(id);
            }
        }
        return "redirect:/home";
    }

    
    @RequestMapping(path="/editPost/{id}", method = {RequestMethod.PATCH, RequestMethod.GET})
    public String editPost(@PathVariable Long id, Model model) {
    	Post post = new Post();
        if(id > 0) {
            post = postService.getById(id);
            if(post != null) {
            	model.addAttribute("post", post);
                return "edit-post-form";
            }
        }
        return "redirect:/home";
    }

	@GetMapping(value = "/images/{filename:.+}")
	public ResponseEntity<Resource> displayImage(@PathVariable String filename) {
		Resource resource = null;
		try {
			resource = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}

