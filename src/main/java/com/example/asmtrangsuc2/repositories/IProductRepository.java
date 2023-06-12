package com.example.asmtrangsuc2.repositories;

import java.util.List;

import com.example.asmtrangsuc2.entities.Account;
import com.example.asmtrangsuc2.entities.Product;
import com.example.asmtrangsuc2.models.HangBanChay;
import com.example.asmtrangsuc2.models.HangTon;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
	Page<Product> findByNameContains(String name, Pageable pageable);

	@Query("select c from Product c where c.status = 1")
	List<Product> findAllActive();

	@Query("select c from Product c where c.status = 1")
	Page<Product> findAllActive(Pageable pageable);

	@Query("select c from Product c where c.status = 1")
	List<Product> findAllActive(Sort sort);

	@Query("select c from Product c where c.categoryById.id = :id")
	List<Product> findAllByCategoryId(@Param("id") Integer id);

	@Query("SELECT new com.example.asmtrangsuc2.models.HangBanChay(od.productById.id, od.productById.name, SUM(od.quantity)) FROM OrderDetail od GROUP BY od.productById.id, od.productById.name ORDER BY SUM(od.quantity) DESC")
	Page<HangBanChay> findTopSellingProducts(Pageable pageable);

	@Query("SELECT new com.example.asmtrangsuc2.models.HangTon(dq.id, dq.name, dq.quantity) FROM Product dq ORDER BY dq.quantity DESC")
	Page<HangTon> findTopHangTonNhieuNhat(Pageable pageable);


	@Modifying
	@Transactional
	@Query("UPDATE Product d SET d.quantity = d.quantity - :quantity WHERE d.id = :id")
	void updateInventory(@Param("id") Integer id, @Param("quantity") int quantity);


}