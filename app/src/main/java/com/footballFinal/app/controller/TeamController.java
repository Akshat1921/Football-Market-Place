package com.footballFinal.app.controller;

import com.footballFinal.app.models.League;
import com.footballFinal.app.models.Player;
import com.footballFinal.app.models.Team;
import com.footballFinal.app.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("football/league/{leagueId}/team")
public class TeamController {
    @Autowired
    private TeamService teamService;
    @PostMapping
    public ResponseEntity<Team> createTeam(@PathVariable Long leagueId, @RequestBody Team team) {
        Team createdTeam = teamService.createTeam(leagueId, team);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
    }

    @PostMapping("/saveteams")
    ResponseEntity<List<Team>> saveTeams(@PathVariable Long leagueId, @RequestBody List<Team> teams){
        List<Team> teamList = teamService.createTeams(leagueId,teams);
        return ResponseEntity.status(HttpStatus.CREATED).body(teamList);
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeamsByLeagueId(@PathVariable Long leagueId) {
        List<Team> teams = teamService.getAllTeamsByLeagueId(leagueId);
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long teamId) {
        Team team = teamService.getTeamById(teamId);
        return ResponseEntity.ok(team);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long teamId, @RequestBody Team team) {
        team.setId(teamId);
        Team updatedTeam = teamService.updateTeam(team);
        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeamById(@PathVariable Long teamId) {
        teamService.deleteTeamById(teamId);
        return ResponseEntity.noContent().build();
    }
}
