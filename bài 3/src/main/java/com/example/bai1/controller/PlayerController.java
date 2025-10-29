package com.example.bai1.controller;

import com.example.bai1.entity.Player;
import com.example.bai1.service.IPlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("players")
public class PlayerController {
    private final IPlayerService iPlayerService;

    public PlayerController(IPlayerService iPlayerService) {
        this.iPlayerService = iPlayerService;
    }

    @GetMapping
    public String listPlayer(Model model){
        List<Player> playerList = iPlayerService.findAll();
        model.addAttribute("playerList", playerList);
        return "football_player/list";
    }
    @GetMapping("/detail/{id}")
    public String detailPlayer(@PathVariable("id") int id, Model model){
        model.addAttribute("player", iPlayerService.findById(id));
        return "football_player/detail";
    }
    @PostMapping("/delete/{id}")
    public String deletePlayer(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        iPlayerService.deleteById(id);
        redirectAttributes.addFlashAttribute("mess", "Xoá cầu thủ thành công!");
        return "redirect:/players";
    }
    @GetMapping("/add")
    public String addPlayer(Model model){
        model.addAttribute("player", new Player());
        return "football_player/add";
    }
    @PostMapping("/add")
    public String addPlayer(@ModelAttribute Player player, Model model,RedirectAttributes redirectAttributes){
        iPlayerService.save(player);
        model.addAttribute("player",player);
        redirectAttributes.addFlashAttribute("mess", "Thêm mới cầu thủ thành công!");
        return "redirect:/players";
    }
}
