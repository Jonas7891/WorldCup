package com.worldcup.worldcup.modules.results.participantOfficialParty.services.interfaces;

import com.worldcup.worldcup.modules.results.participantOfficialParty.dto.ParticipantOfficialPartyDTO;
import com.worldcup.worldcup.modules.results.participantOfficialParty.entity.ParticipantOfficialParty;

import java.util.List;

public interface IParticipantOfficialParty {
    String Create(ParticipantOfficialPartyDTO dto);
    List<ParticipantOfficialParty> GetAll();
    ParticipantOfficialParty GetById(Integer id);
    ParticipantOfficialParty Update(Integer id, ParticipantOfficialPartyDTO dto);
    ParticipantOfficialParty PartialUpdate(Integer id, ParticipantOfficialPartyDTO dto);
    boolean Delete(Integer id);
}

