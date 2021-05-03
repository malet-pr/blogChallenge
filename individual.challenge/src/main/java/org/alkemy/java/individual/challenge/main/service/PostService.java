package org.alkemy.java.individual.challenge.main.service;

import java.util.List;

import org.alkemy.java.individual.challenge.main.dto.PostDto;
import org.alkemy.java.individual.challenge.main.model.Post;

public interface PostService {
		
	public List<Post> getAll();
 	public Post getById(Long id);
	public void save(Post post);
	public void delete(Long id);
    public PostDto modelToDto(Post post);
    public Post dtoToModel(PostDto postDto);
	
}
