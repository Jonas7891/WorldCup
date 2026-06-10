package com.worldcup.worldCup.modules.results.oficialMatches.service;

import com.worldcup.worldCup.modules.results.oficialMatches.dto.request.OficialMatchCreateRequest;
import com.worldcup.worldCup.modules.results.oficialMatches.dto.request.OficialMatchUpdateRequest;
import com.worldcup.worldCup.modules.results.oficialMatches.dto.response.OficialMatchResponse;

import java.util.List;

public interface OficialMatchService {
    List<OficialMatchResponse> getAllMatches();

    OficialMatchResponse getMatchById();

    OficialMatchResponse createMatch(OficialMatchCreateRequest request);

    OficialMatchResponse updateMatch(OficialMatchUpdateRequest request);

    void deleteMatch(Long id);
}
