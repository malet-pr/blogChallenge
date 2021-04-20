package org.alkemy.java.individual.challenge.main.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import org.alkemy.java.individual.challenge.main.model.Post;
import org.alkemy.java.individual.challenge.main.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("post")
public class PostController  {
	
	@Autowired
	private PostService postService;

	@PersistenceContext
    EntityManager em;
	
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
    public String addCourse(@Valid @ModelAttribute Post post, BindingResult bindingResult, SessionStatus status) {	 
		if(bindingResult.hasErrors()) {	    
			return "add-post-form";
		}
		postService.save(post);
		status.isComplete();
        return "redirect:/home";   
	}

	
}

