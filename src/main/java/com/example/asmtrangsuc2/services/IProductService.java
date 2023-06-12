package com.example.asmtrangsuc2.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.example.asmtrangsuc2.entities.Account;
import com.example.asmtrangsuc2.entities.Product;
import com.example.asmtrangsuc2.models.HangBanChay;
import com.example.asmtrangsuc2.models.HangTon;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;


public interface IProductService {

	<S extends Product> List<S> findAll(Example<S> example, Sort sort);

	<S extends Product> List<S> findAll(Example<S> example);

	Product getReferenceById(Integer id);

	Product getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Product> entities);

	Product getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	<S extends Product, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Product entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	<S extends Product> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Product> entities);

	<S extends Product> long count(Example<S> example);

	void deleteInBatch(Iterable<Product> entities);

	<S extends Product> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Product> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Product> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	void flush();

	<S extends Product> List<S> saveAll(Iterable<S> entities);

	Optional<Product> findById(Integer id);

	List<Product> findAllById(Iterable<Integer> ids);

	List<Product> findAll(Sort sort);

	Page<Product> findAll(Pageable pageable);

	List<Product> findAll();

	<S extends Product> Optional<S> findOne(Example<S> example);

	<S extends Product> S save(S entity);

	List<Product> findAllActive();
	
	Page<Product> findAllActive(Pageable pageable);
	
	List<Product> findAllActive(Sort sort);
	
	List<Product> findAllByCategoryId(Integer id);

	void capNhatSoLuongton(Integer id, int quantity);
	Page<HangBanChay> findTopSellingProducts(Pageable pageable);
	Page<HangTon> findTopHangTonNhieuNhat(Pageable pageable);

	Page<Product> findByNameContains (String name, Pageable pageable);


}
