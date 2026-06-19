package com.worldcup.modules.results.service.implement;

import com.worldcup.modules.mundial.entity.Phase;
import com.worldcup.modules.mundial.repository.PhaseRepository;
import com.worldcup.modules.mundial.entity.Stadium;
import com.worldcup.modules.mundial.repository.StadiumRepository;
import com.worldcup.modules.results.dto.request.OficialMatchCreateRequest;
import com.worldcup.modules.results.dto.response.OficialMatchResponse;
import com.worldcup.modules.results.entity.OficialMatch;
import com.worldcup.modules.results.mapper.OficialMatchMapper;
import com.worldcup.modules.results.repository.OficialMatchRepository;
import com.worldcup.modules.results.service.interfaces.OficialMatchService;
import com.worldcup.modules.results.validator.OficialMatchValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OficialMatchServiceImp implements OficialMatchService {


    private final OficialMatchRepository oficialMatchRepository;
    private final OficialMatchMapper oficialMatchMapper;
    private final PhaseRepository phaseRepository;
    private final StadiumRepository stadiumRepository;
    private final OficialMatchValidator oficialMatchValidator;

    @Override
    public List<OficialMatchResponse> getAllMatches() {
        return oficialMatchRepository.findAll()
                .stream()
                .map(oficialMatchMapper::toResponse)
                .toList();
    }

    @Override
    public OficialMatchResponse getMatchById(Integer id) {
        OficialMatch oficialMatch = oficialMatchRepository.findById(id).orElseThrow();
        return oficialMatchMapper.toResponse(oficialMatch);
    }

    @Override
    public OficialMatchResponse createMatch(OficialMatchCreateRequest request) {
        oficialMatchValidator.validateMatchCreation(request);

        Phase phase = phaseRepository.findById(request.getId_phase())
                .orElseThrow(() -> new RuntimeException("Phase not found"));

        Stadium stadium = stadiumRepository.findById(request.getId_stadium())
                .orElseThrow(() -> new RuntimeException("Stadium not found"));

        OficialMatch oficialMatch = new OficialMatch();

        oficialMatch.setPhase(phase);
        oficialMatch.setStadium(stadium);
        oficialMatch.setDate(request.getDate());
        oficialMatch.setOficialMatchStatus(request.getOficialMatchStatus());

        OficialMatch savedMatch =
                oficialMatchRepository.save(oficialMatch);

        return oficialMatchMapper.toResponse(savedMatch);
    }

    @Override
    public OficialMatchResponse updateMatch(Integer id, OficialMatchCreateRequest request) {
        OficialMatch oficialMatch = oficialMatchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Official Match not found with ID: " + id));

        Phase phase = phaseRepository.findById(request.getId_phase())
                .orElseThrow(() -> new RuntimeException("Phase not found"));
        oficialMatch.setPhase(phase);

        Stadium stadium = stadiumRepository.findById(request.getId_stadium())
                .orElseThrow(() -> new RuntimeException("Stadium not found"));
        oficialMatch.setStadium(stadium);

        oficialMatch.setDate(request.getDate());
        oficialMatch.setOficialMatchStatus(request.getOficialMatchStatus());

        OficialMatch updatedMatch = oficialMatchRepository.save(oficialMatch);

        return oficialMatchMapper.toResponse(updatedMatch);
    }

    @Override
    public void deleteMatch(Integer id) {
        oficialMatchRepository.deleteById(id);
    }
}
