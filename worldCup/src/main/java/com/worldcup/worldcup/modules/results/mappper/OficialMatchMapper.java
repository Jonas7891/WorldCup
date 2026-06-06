package com.worldcup.worldcup.modules.results.mappper;

import com.worldcup.worldcup.modules.results.dto.response.OficialMatchResponse;
import com.worldcup.worldcup.modules.results.entity.OficialMatch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OficialMatchMapper {

    @Mapping(target = "Phase", expression = "java(oficialMatch.getPhase().getName())")
    @Mapping(target = "Stadium", expression = "java(oficialMatch.getStadium().getName())")

    OficialMatchResponse toResponse(OficialMatch oficialMatch);


}
