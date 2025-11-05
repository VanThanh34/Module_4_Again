package com.example.baitap.controller;


import com.example.baitap.entity.Player;
import com.example.baitap.service.IPlayerService;
import com.example.baitap.service.ITeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;


@Controller
@RequestMapping("players")
@RequiredArgsConstructor
public class PlayerController {
    private final IPlayerService iPlayerService;
    private final ITeamService iTeamService;

    @GetMapping
    public String listPlayers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "6") int size,
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model
    ) {
        Page<Player> players = iPlayerService.searchPlayers(keyword, startDate, endDate, page, size);

        model.addAttribute("playerList", players);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", players.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("size", size);

        return "football_player/list";
    }

    @GetMapping("/{id}")
    public String detailPlayer(@PathVariable int id, Model model) {
        model.addAttribute("player", iPlayerService.findById(id));
        return "football_player/detail";
    }

    @PostMapping("/delete/{id}")
    public String deletePlayer(@PathVariable int id, RedirectAttributes redirectAttributes) {
        iPlayerService.deleteById(id);
        redirectAttributes.addFlashAttribute("mess", "Xoá cầu thủ thành công!");
        return "redirect:/players";
    }

    @GetMapping("/add")
    public String addPlayer(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("teams", iTeamService.findAll());
        return "football_player/add";
    }

    @PostMapping("/add")
    public String addPlayer(@ModelAttribute Player player, RedirectAttributes redirectAttributes) {
        iPlayerService.save(player);
        redirectAttributes.addFlashAttribute("mess", "Thêm mới cầu thủ thành công!");
        return "redirect:/players";
    }
}
