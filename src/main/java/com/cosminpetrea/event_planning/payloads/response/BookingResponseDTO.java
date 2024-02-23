package com.cosminpetrea.event_planning.payloads.response;

import java.util.UUID;

public record BookingResponseDTO(UUID id, String title, String description) {
}
