package com.footballFinal.app.service;

import com.footballFinal.app.exception.LeagueExceptions.DuplicateLeagueException;
import com.footballFinal.app.exception.LeagueExceptions.LeagueNotFoundException;
import com.footballFinal.app.exception.PlayerExceptions.DuplicatePlayerException;
import com.footballFinal.app.exception.PlayerExceptions.PlayerNotFoundException;
import com.footballFinal.app.exception.TeamExceptions.TeamNotFoundException;
import com.footballFinal.app.models.League;
import com.footballFinal.app.models.Player;
import com.footballFinal.app.models.Team;
import com.footballFinal.app.repository.PlayerRepository;
import com.footballFinal.app.repository.TeamRepository;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Override
    public Player createPlayer(Long teamId, Player player) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if (teamOptional.isEmpty()) {
            throw new TeamNotFoundException("Team not found with ID: " + teamId);
        }
        Team team = teamOptional.get();
        boolean playerExists = playerRepository.existsByNameAndTeam(player.getName(), team);
        if (playerExists) {
            throw new DuplicatePlayerException("Player already exists in the team.");
        }
        player.setTeam(team);
        return playerRepository.save(player);
    }

    @Override
    public List<Player> createPlayers(Long teamId, List<Player> players){
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if (teamOptional.isEmpty()) {
            throw new TeamNotFoundException("Team not found with ID: " + teamId);
        }
        Team team = teamOptional.get();
        for(Player player:players){
            boolean playerExists = playerRepository.existsByNameAndTeam(player.getName(), team);
            if (playerExists) {
                throw new DuplicatePlayerException("Team already exists in the league.");
            }
            player.setTeam(team);
        }
        return playerRepository.saveAll(players);
    }



    @Override
    public void deletePlayerById(Long playerId) {
        if (!playerRepository.existsById(playerId)) {
            throw new IllegalArgumentException("Player not found with ID: " + playerId);
        }
        playerRepository.deleteById(playerId);
    }

    @Override
    public List<Player> getAllPlayersByTeamId(Long teamId) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if (teamOptional.isEmpty()) {
            throw new IllegalArgumentException("Team not found with ID: " + teamId);
        }
        return playerRepository.findByTeamId(teamId);
    }

    @Override
    public Player getPlayerById(Long playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found with ID: " + playerId));
    }

    @Override
    public Player updatePlayer(Player player) {
        if (!playerRepository.existsById(player.getId())) {
            throw new IllegalArgumentException("Player not found with ID: " + player.getId());
        }
        return playerRepository.save(player);
    }
}
