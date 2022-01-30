package com.revature.amsapi.repository;

import com.revature.amsapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT s FROM Account s WHERE s.customer.customer_id = ?1")
    List<Account> selectAccountsByCustomer(Integer id);
}
