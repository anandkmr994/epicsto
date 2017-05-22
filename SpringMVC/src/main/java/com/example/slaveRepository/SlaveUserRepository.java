package com.example.slaveRepository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by anand on 11/5/17.
 */
public interface SlaveUserRepository extends JpaRepository<User, Integer> {
}
