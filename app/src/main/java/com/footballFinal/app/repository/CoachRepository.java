package com.footballFinal.app.repository;

import com.footballFinal.app.models.Coach;
import com.footballFinal.app.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coach,Long> {
    boolean existsByNameAndTeam(String name, Team team);

    List<Coach> findByTeamId(Long teamId);
}
