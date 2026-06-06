package com.example.behavior_tracking_api;

import jakarta.validation.constraints.NotBlank;

public record EventRequest(
        @NotBlank(message = "Die userId darf nicht leer sein")
        String userId,

        @NotBlank(message = "Der eventType darf nicht leer sein")
        String eventType,

        String productId
) {}
