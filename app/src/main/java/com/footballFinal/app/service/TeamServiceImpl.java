package com.footballFinal.app.service;

import com.footballFinal.app.exception.CoachExceptions.DuplicateCoachException;
import com.footballFinal.app.exception.LeagueExceptions.LeagueNotFoundException;
import com.footballFinal.app.exception.TeamExceptions.*;
import com.footballFinal.app.exception.LeagueExceptions.*;
import com.footballFinal.app.models.Coach;
import com.footballFinal.app.models.League;
import com.footballFinal.app.models.Team;
import com.footballFinal.app.repository.LeagueRepository;
import com.footballFinal.app.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService{
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private LeagueRepository leagueRepository;
    @Autowired
    private LeagueService leagueService;

    @Override
    public Team createTeam(Long leagueId, Team team) {
        Optional<League> leagueOptional = leagueRepository.findById(leagueId);
        if (leagueOptional.isEmpty()) {
            throw new LeagueNotFoundException("League not found with ID: " + leagueId);
        }
        League league = leagueOptional.get();
        boolean teamExists = teamRepository.existsByNameAndLeague(team.getName(), league);
        if (teamExists) {
            throw new DuplicateLeagueException("Team already exists in the league.");
        }
        team.setLeague(league);
        return teamRepository.save(team);
    }

    @Override
    public List<Team> createTeams(Long leagueId, List<Team> teams){
        Optional<League> leagueOptional = leagueRepository.findById(leagueId);
        if (leagueOptional.isEmpty()) {
            throw new TeamNotFoundException("league not found with ID: " + leagueId);
        }
        League league = leagueOptional.get();
        for(Team team:teams){
            boolean teamExists = teamRepository.existsByNameAndLeague(team.getName(), league);
            if (teamExists) {
                throw new DuplicateTeamException("Team already exists in the leaguw.");
            }
            team.setLeague(league);
        }
        return teamRepository.saveAll(teams);
    }

    @Override
    public void deleteTeamById(Long teamId) {
        if (!teamRepository.existsById(teamId)) {
            throw new IllegalArgumentException("Team not found with ID: " + teamId);
        }
        teamRepository.deleteById(teamId);
    }


    @Override
    public List<Team> getAllTeamsByLeagueId(Long leagueId) {
        Optional<League> leagueOptional = leagueRepository.findById(leagueId);
        if (leagueOptional.isEmpty()) {
            throw new IllegalArgumentException("League not found with ID: " + leagueId);
        }
        return teamRepository.findByLeagueId(leagueId);
    }

    @Override
    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found with ID: " + teamId));
    }

    @Override
    public Team updateTeam(Team team) {
        if (!teamRepository.existsById(team.getId())) {
            throw new IllegalArgumentException("Team not found with ID: " + team.getId());
        }
        return teamRepository.save(team);
    }
}
