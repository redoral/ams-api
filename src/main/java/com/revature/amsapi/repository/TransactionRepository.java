package com.revature.amsapi.repository;

import com.revature.amsapi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("SELECT s FROM Transaction s WHERE s.account.account_number = ?1 ORDER BY s.transaction_id DESC")
    List<Transaction> getTransactionsByAccount(Long id);
}
