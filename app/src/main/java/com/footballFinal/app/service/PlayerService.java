package com.footballFinal.app.service;

import com.footballFinal.app.models.Player;
import com.footballFinal.app.models.Team;

import java.util.List;

public interface PlayerService {
//    List<Player> createPlayer(Long teamId, List<Player> players);

    List<Player> createPlayers(Long teamId, List<Player> players);
    Player createPlayer(Long teamId, Player player);
    void deletePlayerById(Long playerId);
    List<Player> getAllPlayersByTeamId(Long teamId);
    Player getPlayerById(Long playerId);
    Player updatePlayer(Player player);
}
