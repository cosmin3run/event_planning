package com.cosminpetrea.event_planning.exceptions.payloads;

import java.time.LocalDate;

public record ErrorDTO(String message, LocalDate timestamp) {
}
