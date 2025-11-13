package com.example.demo_security_17.service.blog;



import com.example.demo_security_17.model.AppUser;
import com.example.demo_security_17.model.blog.Blog;
import com.example.demo_security_17.respository.AppUserRepository;
import com.example.demo_security_17.respository.blog.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService implements IBlogService {

    @Autowired
    private IBlogRepository blogRepository;
    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public List<Blog> findAllByUserName(String username) {
        return blogRepository.findBlogByAppUserUserName(username);
    }

    @Override
    public List<Blog> findAllByUserName() {
        return blogRepository.findAll();
    }

    @Override
    public boolean postBlog(Blog blog,String username) {
//       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//       String tenDangNhap = authentication.getName();
       AppUser appUser = appUserRepository.findByUserName(username);
        blog.setAppUser(appUser);
        blogRepository.save(blog);
        return true;
    }
}
