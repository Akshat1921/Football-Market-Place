package com.footballFinal.app.service;

import com.footballFinal.app.exception.CoachExceptions.DuplicateCoachException;
import com.footballFinal.app.exception.PlayerExceptions.DuplicatePlayerException;
import com.footballFinal.app.exception.TeamExceptions.TeamNotFoundException;
import com.footballFinal.app.models.Coach;
import com.footballFinal.app.models.Player;
import com.footballFinal.app.models.Team;
import com.footballFinal.app.repository.CoachRepository;
import com.footballFinal.app.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachServiceImpl implements CoachService{

    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Coach createCoach(Long teamId, Coach coach) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if (teamOptional.isEmpty()) {
            throw new TeamNotFoundException("Team not found with ID: " + teamId);
        }
        Team team = teamOptional.get();
        boolean coachExists = coachRepository.existsByNameAndTeam(coach.getName(), team);
        if (coachExists) {
            throw new DuplicatePlayerException("Player already exists in the team.");
        }
        coach.setTeam(team);
        return coachRepository.save(coach);
    }

    @Override
    public void deleteCoachById(Long coachId) {
        if (!coachRepository.existsById(coachId)) {
            throw new IllegalArgumentException("Coach not found with ID: " + coachId);
        }
        coachRepository.deleteById(coachId);
    }

    @Override
    public List<Coach> getAllCoachesByTeamId(Long teamId) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if (teamOptional.isEmpty()) {
            throw new IllegalArgumentException("Team not found with ID: " + teamId);
        }
        return coachRepository.findByTeamId(teamId);
    }

    @Override
    public List<Coach> createCoaches(Long teamId, List<Coach> coaches){
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if (teamOptional.isEmpty()) {
            throw new TeamNotFoundException("Team not found with ID: " + teamId);
        }
        Team team = teamOptional.get();
        for(Coach coach:coaches){
            boolean coachExists = coachRepository.existsByNameAndTeam(coach.getName(), team);
            if (coachExists) {
                throw new DuplicateCoachException("Coach already exists in the team.");
            }
            coach.setTeam(team);
        }
        return coachRepository.saveAll(coaches);
    }

    @Override
    public Coach getCoachById(Long coachId) {
        return coachRepository.findById(coachId)
                .orElseThrow(() -> new IllegalArgumentException("Coach not found with ID: " + coachId));
    }

    @Override
    public Coach updateCoach(Coach coach) {
        if (!coachRepository.existsById(coach.getId())) {
            throw new IllegalArgumentException("Coach not found with ID: " + coach.getId());
        }
        return coachRepository.save(coach);
    }
}
