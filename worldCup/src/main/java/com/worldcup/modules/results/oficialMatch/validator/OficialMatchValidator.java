package com.worldcup.modules.results.oficialMatch.validator;

import com.worldcup.modules.results.oficialMatch.dto.request.OficialMatchCreateRequest;
import com.worldcup.modules.results.oficialMatch.repository.OficialMatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OficialMatchValidator {

    private final OficialMatchRepository OficialMatchRepository;

    public void validateMatchCreation(OficialMatchCreateRequest request) {
        // 1. Validar que la fecha del partido no sea en el pasado
        if (request.getDate() != null && request.getDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("The match date cannot be in the past.");
        }

        // 2. Validar que el estado inicial no sea inválido al crearse
        if (request.getOficialMatchStatus() == null) {
            throw new RuntimeException("The official match status is required.");
        }

    }
}
