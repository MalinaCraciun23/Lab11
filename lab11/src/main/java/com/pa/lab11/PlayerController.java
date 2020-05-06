/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.lab11;

import com.pa.lab11.DAO.PlayerDao;
import com.pa.lab11.DTO.Player;
import java.util.Set;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author mally
 */
@RestController
@RequestMapping("/products")
public class PlayerController {

    PlayerDao dao = new PlayerDao();

    public PlayerController() {
        dao.insertPlayer(new Player("Player1", 11));
        dao.insertPlayer(new Player("Player2", 22));
        dao.insertPlayer(new Player("Player3", 33));
    }

    @GetMapping
    public Set<Player> getProducts() {
        return dao.getAllPlayers();
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestParam String name, @RequestParam Integer age) {
        dao.insertPlayer(new Player(name, age));
        return new ResponseEntity<>("Post successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestParam String name, @RequestParam Integer age) {
        dao.updatePlayer(new Player(id, name, age));
        return new ResponseEntity<>("Put successfully", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        dao.deletePlayer(id);
        return new ResponseEntity<>("Delete successfully", HttpStatus.CREATED);
    }
}
