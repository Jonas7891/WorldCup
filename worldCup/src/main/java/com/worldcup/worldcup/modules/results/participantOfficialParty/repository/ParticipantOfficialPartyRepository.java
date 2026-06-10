package com.worldcup.worldCup.modules.results.participantOfficialParty.repository;

import com.worldcup.worldCup.modules.results.participantOfficialParty.entity.ParticipantOfficialParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipantOfficialPartyRepository extends JpaRepository<ParticipantOfficialParty, Integer> {

    @Query("SELECT p FROM participant_official_party p WHERE p.official_match.id_official_match = :official_match_id")
    List<ParticipantOfficialParty> findByOfficialMatchId(@Param("official_match_id") Integer official_match_id);

    @Query("SELECT p FROM participant_official_party p WHERE p.team.id_team = :team_id")
    List<ParticipantOfficialParty> findByTeamId(@Param("team_id") Integer team_id);

    @Query("SELECT p FROM participant_official_party p WHERE p.official_match.id_official_match = :official_match_id AND p.team.id_team = :team_id")
    List<ParticipantOfficialParty> findByOfficialMatchIdAndTeamId(@Param("official_match_id") Integer official_match_id, @Param("team_id") Integer team_id);
}

