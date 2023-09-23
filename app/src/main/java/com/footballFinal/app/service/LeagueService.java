package com.footballFinal.app.service;

import com.footballFinal.app.models.League;

import java.util.List;
import java.util.Optional;

public interface LeagueService {
    List<League> createLeagues(List<League> leagues);
    League saveLeague(League league);
    List<League> getAllLeagues();
    Optional<League> getLeagueById(Long id);
//    League getLeagueByTeam(Team team);

    // Update operation

    Optional<League> updateLeague(League league, Long leagueId);

//    List<League> getLeagueByTeam(Team team);

    // Delete operation
    void deleteLeague(Long leagueId);
}
