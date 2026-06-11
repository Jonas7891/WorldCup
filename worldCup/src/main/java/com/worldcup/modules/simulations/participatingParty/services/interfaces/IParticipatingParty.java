package com.worldcup.modules.simulations.participatingParty.services.interfaces;

import com.worldcup.modules.simulations.participatingParty.dto.ParticipatingPartyDTO;
import com.worldcup.modules.simulations.participatingParty.entity.ParticipatingParty;

import java.util.List;

public interface IParticipatingParty {
    String Create(ParticipatingPartyDTO dto);
    List<ParticipatingParty> GetAll();
    ParticipatingParty GetById(Integer participatingPartyId);
    ParticipatingParty Update(Integer participatingPartyId, ParticipatingPartyDTO dto);
    ParticipatingParty PartialUpdate(Integer participatingPartyId, ParticipatingPartyDTO dto);
    boolean Delete(Integer participatingPartyId);
}

