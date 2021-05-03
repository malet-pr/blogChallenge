package org.alkemy.java.individual.challenge.main.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.alkemy.java.individual.challenge.main.model.Post;
import org.alkemy.java.individual.challenge.main.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.alkemy.java.individual.challenge.main.dto.PostDto;
import org.alkemy.java.individual.challenge.main.exception.custom.InvalidDataException;


@RestController
@RequestMapping("/api/v1")
public class RestPostController {
	
	@Autowired
	private PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getPosts() {
    	
    	List<Post> posts = postService.getAll();
    	List<PostDto> postDtos = posts.stream()
						          .map(postService::modelToDto)
						          .collect(Collectors.toList());
    	
    	HttpHeaders httpHeaders = new HttpHeaders();
    	httpHeaders.add("Method", "getPosts");
    	
    	return ResponseEntity
    			.status(HttpStatus.OK)
    			.headers(httpHeaders)
    			.body(postDtos);
    	
    }
    
	@GetMapping({"/posts/{id}"})
	public ResponseEntity<PostDto> postDetails(@PathVariable Long id) { 
		
		Post post = postService.getById(id);
		PostDto postDto = postService.modelToDto(post);
		
    	HttpHeaders httpHeaders = new HttpHeaders();
    	httpHeaders.add("Method", "viewPostDetails");
    	
    	return ResponseEntity
    			.status(HttpStatus.OK)
    			.headers(httpHeaders)
    			.body(postDto);
    	
	}  

    @PostMapping(path="/addPost", consumes={MediaType.APPLICATION_JSON_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PostDto> addPost(@Valid @RequestBody PostDto postDto, BindingResult result){
    	
        if(result.hasErrors()){
            throw new InvalidDataException(result);
        }
        
        Post post = postService.dtoToModel(postDto);
        post.setId(0L);
        postService.save(post);
        
    	HttpHeaders httpHeaders = new HttpHeaders();
    	httpHeaders.add("Method", "addPost");
    	
    	return ResponseEntity
    			.status(HttpStatus.OK)
    			.headers(httpHeaders)
    			.body(postDto);
    	
    }
    
    @PatchMapping(path="/editPost")
    public ResponseEntity<Void> editPost(@Valid @RequestBody PostDto postDto, BindingResult result) {   
    	
    	if(result.hasErrors()) {
    		throw new InvalidDataException(result);
    	}
    	
        Post post = postService.dtoToModel(postDto);
        postService.save(post);
        
    	HttpHeaders httpHeaders = new HttpHeaders();
    	httpHeaders.add("Method", "addPost");
    	
    	return ResponseEntity
    			.status(HttpStatus.ACCEPTED)
    			.headers(httpHeaders)
    			.build();
    	
	    }
 
	
    @DeleteMapping(path="/deletePost/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) { 
    	
    	Post post = postService.getById(id);
        if(post != null) {
            postService.delete(id);
        }
        
    	HttpHeaders httpHeaders = new HttpHeaders();
    	httpHeaders.add("Method", "deletePost");
    	
    	return ResponseEntity
    			.status(HttpStatus.ACCEPTED)
    			.headers(httpHeaders)
    			.build();
    	
    }
    
}