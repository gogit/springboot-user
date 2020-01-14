package com.thinktag.user.controller;

import com.thinktag.user.model.User;
import com.thinktag.user.service.JwtClientAdapter;
import com.thinktag.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserService users;

    @Autowired
    JwtClientAdapter jwtClient;

    @PostMapping("public/api/register")
    String register(
            @NotNull @RequestParam("username") final String username,
            @NotNull @RequestParam("password") final String password) {
        users.save(new User(username,
                username,
                password));
        //TODO persist
        return UUID.randomUUID().toString();
    }

    @PostMapping("public/api/login")
    String login(
            @NotNull @RequestParam("username") final String username,
            @NotNull @RequestParam("password") final String password) {

        Optional<User> ouser = users.findByUsername(username);
        if (!ouser.isPresent()) {
            return "invalid";
        }
        if (!Objects.equals(ouser.get().getPassword(), password)) {
            //TODO lock account after number of tries
            return "invalid";
        }
        return jwtClient.createToken(username);
    }
}