package com.example.baitap.controller;

import com.example.baitap.dto.PlayerDto;
import com.example.baitap.entity.Player;
import com.example.baitap.service.IPlayerService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/players")
public class RestPlayerController {
    private final IPlayerService playerService;

    public RestPlayerController(IPlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAll(){
        List<Player> playerList = playerService.findAll();
        if(playerList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(playerList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> detail(@PathVariable("id") int id){
        Player player = playerService.findById(id);
        if(player == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Player> add(@RequestBody PlayerDto playerDto){
        Player player = new Player();
        BeanUtils.copyProperties(playerDto, player);
        playerService.save(player);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Player> update(@PathVariable("id") int id,
                                         @RequestBody PlayerDto playerDto){
        Player player = playerService.findById(id);
        if(player == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(playerDto,player);
        return new ResponseEntity<>(player,HttpStatus.OK);
    }
}
