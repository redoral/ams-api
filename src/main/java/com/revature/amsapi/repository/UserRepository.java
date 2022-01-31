package com.revature.amsapi.repository;

import com.revature.amsapi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query("SELECT s FROM Users s WHERE s.username = ?1 AND s.password = ?2")
    Users loginUser(String username, String password);
}
