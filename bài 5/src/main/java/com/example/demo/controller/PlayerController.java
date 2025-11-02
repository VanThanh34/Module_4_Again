package com.example.demo.controller;

import com.example.demo.entity.Player;
import com.example.demo.service.IPlayerService;
import com.example.demo.service.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/players")
public class PlayerController {

    private final IPlayerService playerService = new PlayerService();

    @GetMapping
    public String list(Model model) {
        model.addAttribute("players", playerService.findAll());
        return "football_player/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("player", new Player());
        return "football_player/add";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Player player) {
        playerService.save(player);
        return "redirect:/players";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model) {
        model.addAttribute("player", playerService.findById(id));
        return "football_player/detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        playerService.delete(id);
        return "redirect:/players";
    }
}
