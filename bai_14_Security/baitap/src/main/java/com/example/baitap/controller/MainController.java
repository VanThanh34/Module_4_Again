package com.example.baitap.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.WebUtils;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    // 🏠 Trang chủ / Welcome
    @GetMapping({"/", "/welcome"})
    public String welcomePage(Model model, Principal principal) {

        String message;
        if (principal != null) {
            message = "Bạn đã đăng nhập vào hệ thống! Hãy trải nghiệm nhé 👋";
            System.out.println("------ Username: " + principal.getName());
        } else {
            message = "Xin chào! Hãy đăng nhập để trải nghiệm tốt hơn 💻";
            System.out.println("------ Chưa đăng nhập ------");
        }

        model.addAttribute("message", message);
        return "welcomePage";
    }

    // 🔐 Trang đăng nhập
    @GetMapping("/login")
    public String loginPage() {
        return "loginPage";
    }

    // 🚪 Trang logout thành công
    @GetMapping("/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        System.out.println("✅ Đăng xuất thành công");
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    // 👤 Trang thông tin người dùng
    @GetMapping("/userInfo")
    public String userInfo(Model model, Authentication authentication) {

        if (authentication == null) {
            model.addAttribute("username", "Chưa đăng nhập");
            model.addAttribute("roles", List.of());
            return "userInfoPage";
        }

        String username = authentication.getName();
        List<String> rolesList = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        model.addAttribute("username", username);
        model.addAttribute("roles", rolesList);

        return "userInfoPage";
    }

    // 🛠 Trang dành cho Admin
    @GetMapping("/admin")
    public String adminPage(Model model, Authentication authentication) {
        if (authentication == null) {
            model.addAttribute("userInfo", "Không có người dùng đăng nhập");
            return "adminPage";
        }

        User loggedUser = (User) authentication.getPrincipal();
        String userInfo = "Username: " + loggedUser.getUsername()
                + ", Roles: " + loggedUser.getAuthorities();


        model.addAttribute("userInfo", userInfo);
        return "adminPage";
    }

    // 🚫 Trang 403 - Không có quyền truy cập
    @GetMapping("/403")
    public String accessDenied(Model model, Authentication authentication) {

        if (authentication != null) {
            User loggedUser = (User) authentication.getPrincipal();
            String userInfo = "Username: " + loggedUser.getUsername()
                    + ", Roles: " + loggedUser.getAuthorities();

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + authentication.getName()
                    + ", bạn không có quyền truy cập vào trang này ❌";
            model.addAttribute("message", message);
        } else {
            model.addAttribute("message", "Bạn không có quyền truy cập vào trang này ❌");
        }

        return "403Page";
    }
}
