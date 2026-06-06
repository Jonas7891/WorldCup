package com.worldcup.worldcup.modules.results.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class OficialMatchCreateRequest {

    private Long id_phase;

    private Long id_stadium;

    private LocalDateTime date;

}
