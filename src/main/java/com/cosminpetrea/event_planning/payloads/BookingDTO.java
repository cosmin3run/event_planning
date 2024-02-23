package com.cosminpetrea.event_planning.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record BookingDTO(
        @NotNull(message = "Event Id is mandatory")
        UUID event,
        @NotNull(message = "number of participants is mandatory")
        int seatsNumber,
        @NotNull(message = "User Id is mandatory")
        UUID user) {
}
