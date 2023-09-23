package com.footballFinal.app.repository;


import com.footballFinal.app.models.League;
import com.footballFinal.app.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeagueRepository extends JpaRepository<League,Long> {
//    League findById(Long id);
    List<League> findByTeamsContains(Team team);
}
