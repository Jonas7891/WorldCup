package com.worldcup.modules.results.service.interfaces;

import com.worldcup.modules.results.dto.request.OficialMatchParticipantsCreateRequest;
import com.worldcup.modules.results.dto.response.OficialMatchParticipantsResponse;
import java.util.List;

public interface OficalMatchParticipantsService {
    List<OficialMatchParticipantsResponse> getAllMatchParticpants();

    OficialMatchParticipantsResponse getMatchParticipantsById(Integer id);

    OficialMatchParticipantsResponse createMatchParticpants(OficialMatchParticipantsCreateRequest request);

    OficialMatchParticipantsResponse updateMatchParticipants(Integer id, OficialMatchParticipantsCreateRequest request);

    void deleteMatchParticipants(Integer id);



}
