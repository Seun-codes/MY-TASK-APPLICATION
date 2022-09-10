package com.elizabeth.mytodoapp.Repository;

import com.elizabeth.mytodoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM users WHERE email = ?1" , nativeQuery = true)
   public User findByEmail(String email);
    @Query(value = "SELECT * FROM users WHERE email= ?1 AND password =?2", nativeQuery = true )
   public User findByEmailAndPasswordAnd(String email, String password);
}
