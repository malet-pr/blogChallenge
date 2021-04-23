package org.alkemy.java.individual.challenge.main.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "posts")
public class Post implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final int MIN_TITLE_LENGTH = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = MIN_TITLE_LENGTH, message = "El título debe tener al menos " + MIN_TITLE_LENGTH + " caracteres.")
    @NotNull(message = "El título no puede estar vacío.")
    @Column(name = "title", nullable = false)
    private String title;

    @NotEmpty(message = "El artículo debe tener algún contenido.")
    @Column(name = "body", length=350, nullable = false)
    private String body;
    
    @Column(name = "category")
    private String category;

    @Column(name = "image")
    private String image;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "removed_at")
    private Date removedAt;
    
    public Post() {}

	/**
	 * @param id
	 * @param title
	 * @param category
	 * @param body
	 * @param image
	 */
	public Post(Long id, String title, String category, String body, String image) {
		this.id = id;
		this.title = title;
		this.category = category;
		this.body = body;
		this.image = image

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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getRemovedAt() {
		return removedAt;
	}

	public void setRemovedAt(Date removedAt) {
		this.removedAt = removedAt;
	}
		
	
}
