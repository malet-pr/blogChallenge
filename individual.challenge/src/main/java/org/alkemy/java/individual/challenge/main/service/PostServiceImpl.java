package org.alkemy.java.individual.challenge.main.service;

import java.util.List;

import org.alkemy.java.individual.challenge.main.dto.PostDto;
import org.alkemy.java.individual.challenge.main.model.Post;
import org.alkemy.java.individual.challenge.main.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Post> getAll() {
		return postRepository.findAll(Sort.by(Sort.Direction.DESC, "creationDate"));
	}

	@Override
	@Transactional(readOnly = true)
	public Post getById(Long id) {
		return postRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Post post) {
		postRepository.save(post);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		postRepository.deleteById(id);
	}
	
	
    public PostDto modelToDto(Post post){
    	ModelMapper mapper = new ModelMapper();
        PostDto postDto = mapper.map(post, PostDto.class);
        return postDto;
    }

    public Post dtoToModel(PostDto postDto){
        ModelMapper mapper = new ModelMapper();
        Post post = mapper.map(postDto, Post.class);
        return post;
    }

}


