package com.worldcup.modules.results.oficialMatchParticipants.mapper;

import com.worldcup.modules.results.oficialMatchParticipants.dto.response.OficialMatchParticipantsResponse;
import com.worldcup.modules.results.oficialMatchParticipants.entity.OficialMatchParticipants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OficialMatchParticipantsMapper {
    @Mapping(target = "team", source = "team.name")
    @Mapping(target = "id_oficialMatch", source = "oficialMatch.id_oficialMatch")

    OficialMatchParticipantsResponse toResponse(OficialMatchParticipants oficialMatchParticipants);

}
