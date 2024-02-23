package com.cosminpetrea.event_planning.controllers;

import com.cosminpetrea.event_planning.entities.User;
import com.cosminpetrea.event_planning.exceptions.BadRequestException;
import com.cosminpetrea.event_planning.payloads.UserDTO;
import com.cosminpetrea.event_planning.payloads.login.UserLoginDTO;
import com.cosminpetrea.event_planning.payloads.login.UserLoginResponseDTO;
import com.cosminpetrea.event_planning.payloads.response.UserRespDTO;
import com.cosminpetrea.event_planning.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO payload){
        String accessToken = authService.authenticateUserAndGenerateToken(payload);
        return new UserLoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRespDTO createUser(@RequestBody @Validated UserDTO newUserPayload, BindingResult validation){
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        } else {
            User newUser = authService.saveUser(newUserPayload);
            return new UserRespDTO(newUser.getId(), newUser.getUsername());
        }
    }
}
