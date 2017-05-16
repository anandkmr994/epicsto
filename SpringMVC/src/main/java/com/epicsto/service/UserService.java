package com.epicsto.service;

import com.epicsto.entity.User;
import com.epicsto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by anandkumar on 9/5/17.
 */
@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository ;

    public int createUser(User user){
        User userCreated = userRepository.save(user);
        if (null != userCreated){
            return userCreated.getId();
        }
        return -1 ;
    }



}
