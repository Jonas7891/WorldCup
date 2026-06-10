package com.worldcup.worldCup.modules.results.oficialMatches.mappper;

import com.worldcup.worldCup.modules.results.oficialMatches.dto.response.OficialMatchResponse;
import com.worldcup.worldCup.modules.results.oficialMatches.entity.OficialMatch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OficialMatchMapper {

    @Mapping(target = "phase", expression = "java(oficialMatch.getPhase().getName())")
    @Mapping(target = "stadium", expression = "java(oficialMatch.getStadium().getName())")

    OficialMatchResponse toResponse(OficialMatch oficialMatch);
}
