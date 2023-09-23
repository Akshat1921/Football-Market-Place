package com.footballFinal.app.service;

import com.footballFinal.app.models.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<Team> createTeams(Long leagueId, List<Team> teams);
    Team createTeam(Long leagueId, Team team);
    void deleteTeamById(Long teamId);
    List<Team> getAllTeamsByLeagueId(Long leagueId);
    Team getTeamById(Long teamId);
    Team updateTeam(Team team);
}
