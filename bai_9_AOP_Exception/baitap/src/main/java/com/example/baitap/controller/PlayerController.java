package com.example.baitap.controller;


import com.example.baitap.dto.PlayerDto;
import com.example.baitap.entity.Player;
import com.example.baitap.exception.ExcessPlayerException;
import com.example.baitap.service.IPlayerService;
import com.example.baitap.service.ITeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;


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
    public String showFormAdd(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("teams", iTeamService.findAll());
        model.addAttribute("positions",
                List.of("trung vệ", "hậu vệ", "tiền vệ", "tiền đạo", "thủ môn")
        );
        return "football_player/add";
    }

    @PostMapping("/add")
    public String save(
            @Valid @ModelAttribute("player") PlayerDto playerDto,
            BindingResult bindingResult,
            Model model) {


        if (!playerDto.isValidAge()) {
            bindingResult.rejectValue("dob", "dob.invalid", "Tuổi phải từ 16 đến 100");
        }


        if (bindingResult.hasErrors()) {
            model.addAttribute("player", playerDto);
            return "football_player/add";
        }

        Player player = new Player();
        BeanUtils.copyProperties(playerDto,player);
        iPlayerService.save(player);
        return "redirect:/players";
    }

    @PostMapping("/toggle/{id}")
    public String togglePlayerStatus(@PathVariable Integer id,
                                     @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                     @RequestParam(value = "size", required = false, defaultValue = "6") int size,
                                     @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                     RedirectAttributes redirectAttributes) {
        try {
            iPlayerService.toggleStatus(id);
        } catch (ExcessPlayerException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/players?page=" + page + "&size=" + size + "&keyword=" + keyword;
    }

    @GetMapping("/team")
    public String showTeam(Model model) {
        List<Player> teamList = iPlayerService.findRegisteredPlayers();
        long count = teamList.size();
        model.addAttribute("teamList", teamList);
        model.addAttribute("count", count);
        return "football_player/team";
    }
}
