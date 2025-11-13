package com.example.demo_security_17.respository.blog;


import com.example.demo_security_17.model.blog.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IBlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findBlogByAppUserUserName(String userName);
}
