package com.footballFinal.app.controller;


import com.footballFinal.app.models.Player;
import com.footballFinal.app.models.Team;
import com.footballFinal.app.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("football/league/{leagueId}/team/{teamId}/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @PostMapping
    ResponseEntity<Player> savePlayer(@PathVariable Long teamId, @RequestBody Player player){
        Player createdPlayer = playerService.createPlayer(teamId, player);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlayer);
    }
    @PostMapping("/saveplayers")
    ResponseEntity<List<Player>> savePlayers(@PathVariable Long teamId,@RequestBody List<Player> players){
        List<Player> playerList = playerService.createPlayers(teamId,players);
        return ResponseEntity.status(HttpStatus.CREATED).body(playerList);
    }
    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayersByTeamId(@PathVariable Long teamId) {
        List<Player> players = playerService.getAllPlayersByTeamId(teamId);
        return ResponseEntity.ok(players);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long playerId) {
        Player player = playerService.getPlayerById(playerId);
        return ResponseEntity.ok(player);
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long playerId, @RequestBody Player player) {
        player.setId(playerId);
        Player updatedPlayer = playerService.updatePlayer(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedPlayer);
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<Void> deletePlayerById(@PathVariable Long playerId) {
        playerService.deletePlayerById(playerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
