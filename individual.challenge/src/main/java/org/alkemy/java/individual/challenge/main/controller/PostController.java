package org.alkemy.java.individual.challenge.main.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.alkemy.java.individual.challenge.main.model.Post;
import org.alkemy.java.individual.challenge.main.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("post")
public class PostController  {
	
	@Autowired
	private PostService postService;

	@PersistenceContext
    EntityManager em;
	
	@GetMapping({"/","/home","/index"})
	public String getHomePage() {   		
		return "home";
	}
	
    @GetMapping("/posts")
    public String getCoursesListA(Model model) {
    	List<Post> posts = postService.getAll();
        model.addAttribute("posts", posts);
        return "posts";
    }
	
}
