package com.example.demo_security_17.service.blog;




import com.example.demo_security_17.model.blog.Blog;

import java.util.List;

public interface IBlogService {
    List<Blog> findAllByUserName(String username);
    List<Blog> findAllByUserName();
    boolean postBlog(Blog blog,String username);
}
