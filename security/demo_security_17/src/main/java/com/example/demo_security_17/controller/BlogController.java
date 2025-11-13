package com.example.demo_security_17.controller;



import com.example.demo_security_17.model.blog.Blog;
import com.example.demo_security_17.service.blog.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/blog")
public class BlogController {

    private final IBlogService blogService;

    public BlogController(IBlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("")
    public String showList(Model model, Principal principal){
        if (principal==null){
            List<Blog> blogList = blogService.findAllByUserName();
            model.addAttribute("blogList",blogList);
        }else {
            String username =principal.getName();
            List<Blog> blogList = blogService.findAllByUserName(username);
            model.addAttribute("blogList",blogList);
        }
        return "blog/list";
    }
    @GetMapping("/create")
    public String showFormCreate(Model model){
        return "blog/create";
    }

    @PostMapping("/create")
    public String save(@RequestParam String title,
                       @RequestParam String content,
                       @RequestParam(defaultValue = "",required = false) String img,
                       Principal principal
                       ){
        String username =principal.getName();
        if (img.equals("")){
         img ="https://baoninhbinh.org.vn//DATA/ARTICLES/2021/5/17/cuoc-dua-lot-vao-top-100-anh-dep-di-san-van-hoa-va-thien-7edf3.jpg";
        }
        blogService.postBlog(new Blog(title,content,img), username);
        return "redirect:/blog";
    }

}
