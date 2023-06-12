package com.example.asmtrangsuc2.repositories;

import java.util.List;

import com.example.asmtrangsuc2.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {
	
	@Query("select c from Account c where c.activated = 1")
	List<Account> findAllActive();
	
	@Query("select c from Account c where c.activated = 1")
	Page<Account> findAllActive(Pageable pageable);
	
	@Query("select c from Account c where c.activated = 1")
	List<Account> findAllActive(Sort sort);
	Page<Account> findByUsernameContains ( String ten,Pageable pageable);


	Account findByUsernameAndPassword(String username, String password);
	Account findByUsernameEquals(String username);
	
	Account findByEmailEquals(String email);
	
	Account findByResetPasswordToken(String token);
}
