package com.footballFinal.app.repository;

import com.footballFinal.app.models.Player;
import com.footballFinal.app.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player,Long> {
    boolean existsByNameAndTeam(String name, Team team);

    List<Player> findByTeamId(Long teamId);
}
