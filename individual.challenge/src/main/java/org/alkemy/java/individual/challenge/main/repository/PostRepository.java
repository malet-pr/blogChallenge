package org.alkemy.java.individual.challenge.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;

import org.alkemy.java.individual.challenge.main.model.Post;

@Repository
@PersistenceContext
public interface PostRepository extends JpaRepository<Post,Long> {


}
