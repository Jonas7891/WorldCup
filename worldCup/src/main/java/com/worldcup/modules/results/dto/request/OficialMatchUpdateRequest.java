package com.worldcup.modules.results.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OficialMatchUpdateRequest {

    private LocalDateTime date;

    private Long id_stadium;


}
