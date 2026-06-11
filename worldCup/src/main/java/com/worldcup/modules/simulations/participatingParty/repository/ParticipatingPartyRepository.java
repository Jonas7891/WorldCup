package com.worldcup.modules.simulations.participatingParty.repository;

import com.worldcup.modules.simulations.participatingParty.entity.ParticipatingParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipatingPartyRepository extends JpaRepository<ParticipatingParty, Integer> {

    @Query("SELECT pp FROM participating_party pp WHERE pp.match.id_match = :matchId")
    List<ParticipatingParty> findByMatchId(@Param("matchId") Integer matchId);

    @Query("SELECT pp FROM participating_party pp WHERE pp.team.id_team = :teamId")
    List<ParticipatingParty> findByTeamId(@Param("teamId") Integer teamId);

    @Query("SELECT pp FROM participating_party pp WHERE pp.match.id_match = :matchId AND pp.team.id_team = :teamId")
    List<ParticipatingParty> findByMatchIdAndTeamId(@Param("matchId") Integer matchId, @Param("teamId") Integer teamId);
}

