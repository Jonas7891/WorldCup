package com.worldcup.modules.results.mapper;

import com.worldcup.modules.results.dto.response.OficialMatchResponse;
import com.worldcup.modules.results.entity.OficialMatch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OficialMatchMapper {

    @Mapping(target = "phase", expression = "java(oficialMatch.getPhase().getName())")
    @Mapping(target = "stadium", expression = "java(oficialMatch.getStadium().getName())")

    OficialMatchResponse toResponse(OficialMatch oficialMatch);


}
