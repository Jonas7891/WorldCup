package com.worldcup.modules.results.oficialMatch.service;

import com.worldcup.modules.results.oficialMatch.dto.request.OficialMatchCreateRequest;
import com.worldcup.modules.results.oficialMatch.dto.response.OficialMatchResponse;

import java.util.List;

public interface OficialMatchService {
    List<OficialMatchResponse> getAllMatches();

    OficialMatchResponse getMatchById(Integer id);

    OficialMatchResponse createMatch(OficialMatchCreateRequest request);

    OficialMatchResponse updateMatch(Integer id, OficialMatchCreateRequest request);

    void deleteMatch(Integer id);
}
