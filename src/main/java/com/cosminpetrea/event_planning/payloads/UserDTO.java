package com.cosminpetrea.event_planning.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserDTO(@NotEmpty(message = "Username is mandatory")
                      @Size(min = 3, max = 20, message = "Username must have at least 3 to maximum 20 characters")
                      String username,
                      @NotEmpty(message = "Name is mandatory")
                      @Size(min = 3, max = 20, message = "Name must have at least 3 to maximum 20 characters")
                      String name,
                      @NotEmpty(message = "Surname is mandatory")
                      @Size(min = 3, max = 20, message = "Surname must have at least 3 to maximum 20 characters")
                      String surname,
                      @Email(message = "Email not valid")
                      String email,
                      @NotEmpty(message = "Password is mandatory")
                      @Size(min = 5, max = 30, message = "password must have at least 6 to maximum 20 characters")
                      String password) {
}
