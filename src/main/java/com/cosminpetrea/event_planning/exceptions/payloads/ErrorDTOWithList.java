package com.cosminpetrea.event_planning.exceptions.payloads;

import java.time.LocalDate;
import java.util.List;

public record ErrorDTOWithList(String message,
                               LocalDate timestamp,
                               List<String> errorsList) {

}
