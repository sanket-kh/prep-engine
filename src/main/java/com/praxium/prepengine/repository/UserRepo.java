package com.praxium.prepengine.repository;

import com.praxium.prepengine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    boolean findByEmailExists(String email);

    User findByEmail(String email);
}
