package com.example.asmtrangsuc2.repositories;

import java.util.List;

import com.example.asmtrangsuc2.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface ICategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select c from Category c where c.status = 1")
	List<Category> findAllActive();
	
	@Query("select c from Category c where c.status = 1")
	Page<Category> findAllActive(Pageable pageable);
	
	@Query("select c from Category c where c.status = 1")
	List<Category> findAllActive(Sort sort);
}
