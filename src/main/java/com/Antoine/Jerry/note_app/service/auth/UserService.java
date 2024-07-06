package com.Antoine.Jerry.note_app.service.auth;

import com.Antoine.Jerry.note_app.model.User;
import com.Antoine.Jerry.note_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }
}
