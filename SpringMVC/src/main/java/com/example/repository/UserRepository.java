package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by anandkumar on 16/5/17.
 */
public interface UserRepository extends JpaRepository<User, Integer>{
        public List<User> findByUserEmail(String email);

        public List<User> findByUserName(String name);
}
