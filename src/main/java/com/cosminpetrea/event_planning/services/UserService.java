package com.cosminpetrea.event_planning.services;


import com.cosminpetrea.event_planning.entities.User;
import com.cosminpetrea.event_planning.enums.Role;
import com.cosminpetrea.event_planning.exceptions.BadRequestException;
import com.cosminpetrea.event_planning.exceptions.NotFoundExceptions;
import com.cosminpetrea.event_planning.payloads.RoleDTO;
import com.cosminpetrea.event_planning.payloads.UserDTO;
import com.cosminpetrea.event_planning.repositories.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UsersDAO usersDAO;

    public List<User> getUsers(){return this.usersDAO.findAll();}
    public User findById(UUID id){return usersDAO.findById(id).orElseThrow(() -> new NotFoundExceptions(id));}
    public User findByEmail(String email){return usersDAO.findByEmail(email).orElseThrow(() -> new NotFoundExceptions("User with email " + email + " does not exist"));}

    public User findByIdAndUpdate(UUID id, UserDTO payload){
        User found = this.findById(id);

        found.setUsername(payload.username());
        found.setName(payload.name());
        found.setSurname(payload.surname());
        found.setEmail(payload.email());
        found.setPassword(payload.password());
        return usersDAO.save(found);
    }

    public User findByIdAndChangeRole(UUID id,String role) {
        User found = this.findById(id);
        if (role.toLowerCase().equals("admin")) {
            found.setRole(Role.ADMIN);
        } else if (role.toLowerCase().equals("user")) {
            found.setRole(Role.USER);
        } else {
            throw new BadRequestException("You can choose ADMIN or USER");
        }
        return usersDAO.save(found);
    }

    public void findByIDAndDelete(UUID id){
        User found = this.findById(id);
        usersDAO.delete(found);
    }
}
