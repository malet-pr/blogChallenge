package org.alkemy.java.individual.challenge.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yuequan.jpa.soft.delete.repository.SoftDelete;
import org.alkemy.java.individual.challenge.main.model.Post;

@Repository
@SoftDelete
public interface PostRepository extends JpaRepository<Post,Long> {


}
