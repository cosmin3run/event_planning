package com.cosminpetrea.event_planning.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EventDTO(@NotEmpty(message = "Title is mandatory")
                       @Size(min = 3, max = 20, message = "This title must have at least 3 to maximum 20 characters")
                       String title,
                       @NotEmpty(message = "Description is mandatory")
                       @Size(min = 3, max = 50, message = "description must have at least 3 to maximum 50 characters")
                       String description,
                       @NotEmpty(message = "Date is mandatory")
                       LocalDate eventDate,
                       @NotEmpty(message = "Location is mandatory")
                       @Size(min = 3, max = 30, message = "Location must have at least 3 characters and max 30")
                       String location,
                       @NotNull(message = "Participants number is mandatory")
                       Integer maxParticipants) {
}
