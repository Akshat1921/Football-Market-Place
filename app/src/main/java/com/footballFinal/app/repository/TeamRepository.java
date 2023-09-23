package com.footballFinal.app.repository;

import com.footballFinal.app.models.League;
import com.footballFinal.app.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team,Long> {
//    List<Team> findByLeagueContains(League league);

    List<Team> findByLeagueId(Long leagueId);

    boolean existsByNameAndLeague(String name, League league);
}
