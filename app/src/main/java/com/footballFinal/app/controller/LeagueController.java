package com.footballFinal.app.controller;

import com.footballFinal.app.models.League;
import com.footballFinal.app.models.Team;
import com.footballFinal.app.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LeagueController {
    @Autowired
    private LeagueService leagueService;

    @GetMapping("football/league")
    public ResponseEntity<List<League>> getAllLeagues(){
        List<League> leagues = leagueService.getAllLeagues();
        if(leagues.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(leagues);
    }

    @PostMapping("football/league/saveleagues")
    ResponseEntity<List<League>> saveLeagues(@RequestBody List<League> leagues){
        List<League> leagueList = leagueService.createLeagues(leagues);
        return ResponseEntity.status(HttpStatus.CREATED).body(leagueList);
    }


    @GetMapping("football/league/{id}")
    public ResponseEntity<Optional<League>> getLeague(@PathVariable Long id){
        Optional<League> league = leagueService.getLeagueById(id);
        if(league.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(league);
    }

    @PostMapping("football/league")
    public ResponseEntity<League> createLeague(@RequestBody League league){
        League league1 = leagueService.saveLeague(league);
        if(league1==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(league1);
    }

    @PutMapping("football/league/{id}")
    public ResponseEntity<Optional<League>> updateLeague(@PathVariable Long id, @RequestBody League league){
        Optional<League> updatedLeague = leagueService.updateLeague(league,id);
        if(updatedLeague.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedLeague);
    }

    @DeleteMapping("football/league/{id}")
    public ResponseEntity<League> deleteLeague(@PathVariable Long id){
        Optional<League> league = leagueService.getLeagueById(id);
        if(league.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        leagueService.deleteLeague(id);
        return ResponseEntity.ok().build();
    }
}
