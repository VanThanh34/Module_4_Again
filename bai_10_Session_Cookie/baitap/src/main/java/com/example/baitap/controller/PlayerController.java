package com.example.baitap.controller;


import com.example.baitap.dto.PlayerDto;
import com.example.baitap.entity.Player;
import com.example.baitap.exception.ExcessPlayerException;
import com.example.baitap.service.IPlayerService;
import com.example.baitap.service.ITeamService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


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
    public String detailPlayer(@PathVariable int id, HttpSession session, Model model) {
        Player player = iPlayerService.findById(id);

        // Kiểm tra trong danh sách yêu thích
        Map<Integer, Player> favorites = (Map<Integer, Player>) session.getAttribute("favorites");
        boolean isFavorite = favorites != null && favorites.containsKey(id);
        model.addAttribute("player", player);
        model.addAttribute("isFavorite", isFavorite);

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

    @GetMapping("/favorites")
    public String showFavorites(HttpSession session,
                                @CookieValue(value = "favoritePlayers", required = false) String cookieValue,
                                Model model) {

        // 🔹 Lấy map từ session (id -> player)
        Map<Integer, Player> favorites = (Map<Integer, Player>) session.getAttribute("favorites");
        if (favorites == null) favorites = new LinkedHashMap<>();

        // 🔹 Nếu session rỗng mà cookie có, khôi phục lại
        if (favorites.isEmpty() && cookieValue != null && !cookieValue.isEmpty()) {
            String[] ids = cookieValue.split(",");
            for (String idStr : ids) {
                try {
                    Integer id = Integer.parseInt(idStr.trim());
                    Player player = iPlayerService.findById(id);
                    if (player != null) favorites.put(id, player);
                } catch (Exception ignored) {}
            }
            session.setAttribute("favorites", favorites);
        }

        List<Player> favoriteList = favorites.values().stream().toList();
        model.addAttribute("favorites", favoriteList);
        return "football_player/favorites";
    }


    @PostMapping("/favorite/{id}")
    public String toggleFavorite(@PathVariable Integer id,
                                 HttpSession session,
                                 HttpServletResponse response,
                                 RedirectAttributes redirectAttributes) {

        // 🔹 Lấy map favorites từ session
        Map<Integer, Player> favorites = (Map<Integer, Player>) session.getAttribute("favorites");
        if (favorites == null) favorites = new LinkedHashMap<>();

        if (favorites.containsKey(id)) {
            favorites.remove(id);
            redirectAttributes.addFlashAttribute("success", "❌ Đã bỏ khỏi danh sách yêu thích!");
        } else {
            Player player = iPlayerService.findById(id);
            if (player != null) {
                favorites.put(id, player);
                redirectAttributes.addFlashAttribute("success", "💖 Đã thêm vào danh sách yêu thích!");
            }
        }

        // 🔹 Lưu lại vào session
        session.setAttribute("favorites", favorites);

        // 🔹 Ghi cookie để giữ lại khi tắt trình duyệt
        String cookieValue = String.join(",",
                favorites.keySet().stream().map(String::valueOf).toList());
        Cookie cookie = new Cookie("favoritePlayers", cookieValue);
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 ngày
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/players/" + id;
    }


}
