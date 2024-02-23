package com.cosminpetrea.event_planning.services;

import com.cosminpetrea.event_planning.entities.User;
import com.cosminpetrea.event_planning.enums.Role;
import com.cosminpetrea.event_planning.exceptions.BadRequestException;
import com.cosminpetrea.event_planning.exceptions.UnauthorizedException;
import com.cosminpetrea.event_planning.payloads.UserDTO;
import com.cosminpetrea.event_planning.payloads.login.UserLoginDTO;
import com.cosminpetrea.event_planning.repositories.UsersDAO;
import com.cosminpetrea.event_planning.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUserAndGenerateToken(UserLoginDTO payload) {
        User user = userService.findByEmail(payload.email());
        if (bcrypt.matches(payload.password(),user.getPassword())){
            return jwtTools.createToken(user);
        }else {
            throw new UnauthorizedException("Email or pssw has been wrongfully added");
        }
    }


    public User saveUser(UserDTO payload) {
        usersDAO.findByEmail(payload.email()).ifPresent(user -> {
            throw new BadRequestException("Email '" + payload.email() + "' is already in use");
        });
        usersDAO.findByUsername(payload.username()).ifPresent(user -> {
            throw new BadRequestException("Username '" + payload.username() + "' is already in use");
        });
        User newUser = new User();
        newUser.setUsername(payload.username());
        newUser.setName(payload.name());
        newUser.setSurname(payload.surname());
        newUser.setEmail(payload.email());
        newUser.setPassword(bcrypt.encode(payload.password()));
        newUser.setRole(Role.USER);
        return usersDAO.save(newUser);
    }
}
