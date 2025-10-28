package com.example.bai1.controller;

import com.example.bai1.entity.Player;
import com.example.bai1.service.IPlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PlayerController {
    private final IPlayerService iPlayerService;

    public PlayerController(IPlayerService iPlayerService) {
        this.iPlayerService = iPlayerService;
    }

    @RequestMapping(value = "player/list", method = RequestMethod.GET)
    public String listPlayer(Model model){
        List<Player> playerList = iPlayerService.findAll();
        model.addAttribute("playerList", playerList);
        return "football_player/list";
    }
}
