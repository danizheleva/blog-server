package com.danielazheleva.blog.repository;

import com.danielazheleva.blog.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends CrudRepository<Post, Long> {

    List<Post> findAll();
}
