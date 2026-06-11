package com.worldcup.modules.results.oficialMatchParticipants.service.Imp;

import com.worldcup.modules.mundial.team.entity.Team;
import com.worldcup.modules.mundial.team.repository.TeamRepository;
import com.worldcup.modules.results.oficialMatch.entity.OficialMatch;
import com.worldcup.modules.results.oficialMatch.repository.OficialMatchRepository;
import com.worldcup.modules.results.oficialMatchParticipants.dto.request.OficialMatchParticipantsCreateRequest;
import com.worldcup.modules.results.oficialMatchParticipants.dto.response.OficialMatchParticipantsResponse;
import com.worldcup.modules.results.oficialMatchParticipants.entity.OficialMatchParticipants;
import com.worldcup.modules.results.oficialMatchParticipants.mapper.OficialMatchParticipantsMapper;
import com.worldcup.modules.results.oficialMatchParticipants.repository.OficialMatchParticipantsRepository;
import com.worldcup.modules.results.oficialMatchParticipants.service.OficalMatchParticipantsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OficialMatchParticipantsServiceImp implements OficalMatchParticipantsService {
    private final OficialMatchParticipantsRepository oficialMatchParticipantsRepository;
    private final OficialMatchParticipantsMapper oficialMatchParticipantsMapper;
    private final TeamRepository teamRepository;
    private final OficialMatchRepository oficialMatchRepository;


    @Override
    public List<OficialMatchParticipantsResponse> getAllMatchParticpants() {
        return oficialMatchParticipantsRepository.findAll()
                .stream()
                .map(oficialMatchParticipantsMapper::toResponse)
                .toList();
    }

    @Override
    public OficialMatchParticipantsResponse getMatchParticipantsById(Integer id) {
        OficialMatchParticipants oficialMatchParticipants = oficialMatchParticipantsRepository.findById(id).orElseThrow();
        return oficialMatchParticipantsMapper.toResponse(oficialMatchParticipants);
    }

    @Override
    public OficialMatchParticipantsResponse createMatchParticpants(OficialMatchParticipantsCreateRequest request) {
        Team team = teamRepository.findById(request.getId_team())
                .orElseThrow(()-> new RuntimeException("Team not found"));

        OficialMatch oficialMatch = oficialMatchRepository.findById(request.getId_oficialMatch())
                .orElseThrow(()-> new RuntimeException("OficialMatch not found"));

        OficialMatchParticipants oficialMatchParticipants = new OficialMatchParticipants();

        oficialMatchParticipants.setTeam(team);
        oficialMatchParticipants.setOficialMatch(oficialMatch);
        oficialMatchParticipants.setGoals(request.getGoals());
        oficialMatchParticipants.setMatchResultStatus(request.getMatchResultStatus());

        OficialMatchParticipants saveMatchParticpants =
                oficialMatchParticipantsRepository.save(oficialMatchParticipants);

        return oficialMatchParticipantsMapper.toResponse(saveMatchParticpants);

    }

    @Override
    public OficialMatchParticipantsResponse updateMatchParticipants(Integer id, OficialMatchParticipantsCreateRequest request) {
        OficialMatchParticipants participants = oficialMatchParticipantsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match Participant not found with ID: " + id));

        Team team = teamRepository.findById(request.getId_team())
                .orElseThrow(() -> new RuntimeException("Team not found with ID: " + request.getId_team()));

        OficialMatch oficialMatch = oficialMatchRepository.findById(request.getId_oficialMatch())
                .orElseThrow(() -> new RuntimeException("OficialMatch not found with ID: " + request.getId_oficialMatch()));

        participants.setTeam(team);
        participants.setOficialMatch(oficialMatch);
        participants.setGoals(request.getGoals());
        participants.setMatchResultStatus(request.getMatchResultStatus());

        OficialMatchParticipants updatedParticipants = oficialMatchParticipantsRepository.save(participants);

        return oficialMatchParticipantsMapper.toResponse(updatedParticipants);
    }

    @Override
    public void deleteMatchParticipants(Integer id) {
        OficialMatchParticipants participants = oficialMatchParticipantsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match Participant not found with ID: " + id));

        oficialMatchParticipantsRepository.delete(participants);
    }
}
