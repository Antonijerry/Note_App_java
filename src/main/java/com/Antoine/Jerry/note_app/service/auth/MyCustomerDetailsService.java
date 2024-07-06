package com.Antoine.Jerry.note_app.service.auth;

import com.Antoine.Jerry.note_app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyCustomerDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);
        //check if user is null
        if(user == null){
            throw new UsernameNotFoundException("Unable to load User");
        }
        return new MyCustomUserDetails(user);
    }
}
