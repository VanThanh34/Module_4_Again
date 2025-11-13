package com.example.demo_security_17.controller;



import com.example.demo_security_17.ultil.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController {

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model,Principal principal) {

        String mess ="";
        if (principal!=null){
            mess = "Bạn đã đang nhập vào hệ thống!. Hãy trải nghiệm";
            System.out.println("------username------:" +principal.getName());
        }else {
            mess = "Xin chào các bạn đến với trang web của chúng tôi! Hãy đăng nhập để trải nghiệm tốt hơn";
            System.out.println("------not login--------------------------------");
        }

        model.addAttribute("message", mess);
        return "welcomePage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "loginPage";
    }


    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        System.out.println("dang xuat thanh cong");
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Authentication authentication) {

        // Sau khi user login thanh cong thông tin user sẽ lưu trong Authentication
        String userName=null;
        List<String> rolesList =new ArrayList<>();
        if (authentication!=null){
            userName = authentication.getName();
            Collection<?extends GrantedAuthority> roles = authentication.getAuthorities();
            for (GrantedAuthority role: roles) {
                rolesList.add(role.getAuthority());
            }
        }
        model.addAttribute("username", userName);
        model.addAttribute("roles", rolesList);

        return "userInfoPage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Authentication authentication) {

        if (authentication != null) {
            User loginedUser = (User) authentication.getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + authentication.getName() //
                    + "<br> Bạn không có quyền truy cập vào trang web này!";
            model.addAttribute("message", message);

        }
        return "403Page";
    }

}