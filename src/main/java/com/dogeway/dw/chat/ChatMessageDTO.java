package com.dogeway.dw.chat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChatMessageDTO(
        @NotNull
        Long sender,
        @NotNull
        Long sendto,
        @NotBlank
        String message
) {
}
