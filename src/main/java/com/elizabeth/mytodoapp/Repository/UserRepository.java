package com.elizabeth.mytodoapp.Repository;

import com.elizabeth.mytodoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByEmailAndPasswordAnd(String email, String password);
}
