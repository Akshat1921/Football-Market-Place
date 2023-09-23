package com.footballFinal.app.service;

import com.footballFinal.app.exception.LeagueExceptions.*;
import com.footballFinal.app.exception.TeamExceptions.DuplicateTeamException;
import com.footballFinal.app.exception.TeamExceptions.TeamNotFoundException;
import com.footballFinal.app.models.League;
import com.footballFinal.app.models.Team;
import com.footballFinal.app.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LeagueServiceImpl implements LeagueService{
    @Autowired
    private LeagueRepository leagueRepository;

    @Override
    public League saveLeague(League league) {
        try {
            return leagueRepository.save(league);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateLeagueException("A league with the same details already exists");
        } catch (Exception e) {
            throw new LeagueSaveException("An error occurred while trying to save the league");
        }
    }

    @Override
    public List<League> getAllLeagues() {
        List<League> leagueList = leagueRepository.findAll();
        if(leagueList.size()>0){
            return leagueList;
        }
        else{
            throw new LeaguesNotFoundException("No leagues found");
        }
//        return leagueRepository.findAll();
    }

    @Override
    public List<League> createLeagues(List<League> leagues){

        if(!leagues.isEmpty()){
            return leagueRepository.saveAll(leagues);
        }
        throw  new LeagueNotFoundException("Leagues not found in the list");
    }

    @Override
    public Optional<League> getLeagueById(Long id) {
        if(id!=null){
            Optional<League> leagueOptional = leagueRepository.findById(id);
            if(leagueOptional.isPresent()){
                return Optional.of(leagueOptional.get());
            }
        }
        throw new LeagueNotFoundException("League not found with ID: " + id);
//        return leagueRepository.findById(id);
    }

//    @Override
//    public List<League> getLeagueByTeam(Team team) {
//        List<League> leagues = leagueRepository.findByTeamsContains(team);
//        if(!leagues.isEmpty()){
//            return leagues;
//        }throw new LeagueNotFoundException("League with team "+team+"does not exists");
////        return leagueRepository.findByTeamsContains(team);
//    }

    @Override
    public Optional<League> updateLeague(League updatedLeague, Long leagueId) {
        Optional<League> leagueOptional = leagueRepository.findById(leagueId);
        if(leagueOptional.isPresent()){
            return Optional.of(leagueRepository.save(leagueOptional.get()));
        }
        throw new LeagueNotFoundException("League not found with ID: " + leagueId);
    }



    @Override
    public void deleteLeague(Long id) {
//        try {
//            leagueRepository.deleteById(id);
//        } catch (EmptyResultDataAccessException e) {
//            throw new LeagueNotFoundException("League with id " + id + " not found");
//        } catch (Exception e) {
//            throw new LeagueDeleteException("An error occurred while trying to delete the league");
//        }

        try{
            leagueRepository.deleteById(id);

        }
        catch(EmptyResultDataAccessException e){
            throw new LeagueNotFoundException("League with id " + id + " not found");
        }
        catch (Exception e){
            throw  new LeagueDeleteException("An error occurred while trying to delete the league");
        }
    }

}
