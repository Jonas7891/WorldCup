package com.worldcup.worldcup.modules.results.service;

import com.worldcup.worldcup.modules.results.dto.request.OficialMatchCreateRequest;
import com.worldcup.worldcup.modules.results.dto.request.OficialMatchUpdateRequest;
import com.worldcup.worldcup.modules.results.dto.response.OficialMatchResponse;

import java.util.List;

public interface OficialMatchService {
    List<OficialMatchResponse> getAllMatches();

    OficialMatchResponse getMatchById();

    OficialMatchResponse createMatch(OficialMatchCreateRequest request);

    OficialMatchResponse updateMatch(OficialMatchUpdateRequest request);

    void deleteMatch(Long id);
}
