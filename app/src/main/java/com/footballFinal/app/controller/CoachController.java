package com.footballFinal.app.controller;

import com.footballFinal.app.models.Coach;
import com.footballFinal.app.models.Player;
import com.footballFinal.app.service.CoachService;
import com.footballFinal.app.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("football/league/{leagueId}/team/{teamId}/coach")
public class CoachController {
    @Autowired
    private CoachService coachService;
    @PostMapping
    ResponseEntity<Coach> saveCoach(@PathVariable Long teamId, @RequestBody Coach coach){
        Coach createdCoach = coachService.createCoach(teamId, coach);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCoach);
    }
//    @PostMapping("/saveplayers")
//    ResponseEntity<List<Player>> savePlayers(@PathVariable Long teamId, @RequestBody List<Player> players){
//        List<Player> playerList = playerService.createPlayers(teamId,players);
//        return ResponseEntity.status(HttpStatus.CREATED).body(playerList);
//    }

    @PostMapping("/savecoaches")
    ResponseEntity<List<Coach>> saveCoaches(@PathVariable Long teamId,@RequestBody List<Coach> coaches){
        List<Coach> coachList = coachService.createCoaches(teamId,coaches);
        return ResponseEntity.status(HttpStatus.CREATED).body(coachList);
    }

    @GetMapping
    public ResponseEntity<List<Coach>> getAllCoachesByTeamId(@PathVariable Long teamId) {
        List<Coach> coaches = coachService.getAllCoachesByTeamId(teamId);
        return ResponseEntity.ok(coaches);
    }

    @GetMapping("/{coachId}")
    public ResponseEntity<Coach> getCoachById(@PathVariable Long coachId) {
        Coach coach = coachService.getCoachById(coachId);
        return ResponseEntity.ok(coach);
    }

    @PutMapping("/{coachId}")
    public ResponseEntity<Coach> updateCoach(@PathVariable Long coachId, @RequestBody Coach coach) {
        coach.setId(coachId);
        Coach updatedCoach = coachService.updateCoach(coach);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCoach);
    }

    @DeleteMapping("/{coachId}")
    public ResponseEntity<Void> deleteCoachById(@PathVariable Long coachId) {
        coachService.deleteCoachById(coachId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
