package com.example.asmtrangsuc2.repositories;

import java.util.List;

import com.example.asmtrangsuc2.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IOrderRepository extends JpaRepository<Order, Integer> {

	@Query("select c from Order c where c.accountById.id = :id")
	List<Order> findAllByAccountId(@Param("id") Integer id);

}
