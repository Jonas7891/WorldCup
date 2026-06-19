package com.worldcup.modules.simulations.repository;

import com.worldcup.modules.simulations.entity.ParticipatingParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipatingPartyRepository extends JpaRepository<ParticipatingParty, Integer> {

    @Query(value = "SELECT * FROM ParticipatingParty pp WHERE pp.match.id_match = :matchId", nativeQuery = true)
    List<ParticipatingParty> findByMatchId(@Param("matchId") Integer matchId);

    @Query(value = "SELECT * FROM ParticipatingParty pp WHERE pp.team.id_team = :teamId", nativeQuery = true)
    List<ParticipatingParty> findByTeamId(@Param("teamId") Integer teamId);

    @Query(value = "SELECT * FROM ParticipatingParty pp WHERE pp.match.id_match = :matchId AND pp.team.id_team = :teamId", nativeQuery = true)
    List<ParticipatingParty> findByMatchIdAndTeamId(@Param("matchId") Integer matchId, @Param("teamId") Integer teamId);
}

