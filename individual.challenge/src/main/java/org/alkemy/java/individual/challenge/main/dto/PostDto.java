package org.alkemy.java.individual.challenge.main.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PostDto {
	@JsonIgnore
    private Long id;
    private String title;
    private String category;
    private String body;
    private String image;
    
    public PostDto() {}

	public PostDto(String title, String category, String body, String image) {
		this.title = title;
		this.category = category;
		this.body = body;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
    
}
