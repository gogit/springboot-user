package com.thinktag.user.service;

import com.thinktag.user.model.User;

import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> find(String id);

    Optional<User> findByUsername(String username);

}
