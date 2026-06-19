package com.worldcup.modules.results.service.interfaces;

import com.worldcup.modules.results.dto.request.OficialMatchCreateRequest;
import com.worldcup.modules.results.dto.response.OficialMatchResponse;

import java.util.List;

public interface OficialMatchService {
    List<OficialMatchResponse> getAllMatches();

    OficialMatchResponse getMatchById(Integer id);

    OficialMatchResponse createMatch(OficialMatchCreateRequest request);

    OficialMatchResponse updateMatch(Integer id, OficialMatchCreateRequest request);

    void deleteMatch(Integer id);
}
