package com.footballFinal.app.service;

import com.footballFinal.app.models.Coach;
import com.footballFinal.app.models.Team;

import java.util.List;

public interface CoachService {
    List<Coach> createCoaches(Long teamId, List<Coach> coaches);
    Coach createCoach(Long teamId, Coach coach);
    void deleteCoachById(Long coachId);
    List<Coach> getAllCoachesByTeamId(Long teamId);
    Coach getCoachById(Long coachId);
    Coach updateCoach(Coach coach);
}
