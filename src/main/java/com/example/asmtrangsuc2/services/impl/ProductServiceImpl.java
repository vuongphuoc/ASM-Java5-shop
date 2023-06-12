package com.example.asmtrangsuc2.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.example.asmtrangsuc2.entities.Account;
import com.example.asmtrangsuc2.entities.Product;
import com.example.asmtrangsuc2.models.HangBanChay;
import com.example.asmtrangsuc2.models.HangTon;
import com.example.asmtrangsuc2.repositories.IProductRepository;
import com.example.asmtrangsuc2.services.IProductService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;



@Service
public class ProductServiceImpl implements IProductService {

	private final IProductRepository productRepository;

	public ProductServiceImpl(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public <S extends Product> S save(S entity) {
		return productRepository.save(entity);
	}

	@Override
	public <S extends Product> Optional<S> findOne(Example<S> example) {
		return productRepository.findOne(example);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public List<Product> findAll(Sort sort) {
		return productRepository.findAll(sort);
	}

	@Override
	public List<Product> findAllById(Iterable<Integer> ids) {
		return productRepository.findAllById(ids);
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return productRepository.findById(id);
	}

	@Override
	public <S extends Product> List<S> saveAll(Iterable<S> entities) {
		return productRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		productRepository.flush();
	}

	@Override
	public boolean existsById(Integer id) {
		return productRepository.existsById(id);
	}

	@Override
	public <S extends Product> S saveAndFlush(S entity) {
		return productRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Product> entities) {
		productRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Product> long count(Example<S> example) {
		return productRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Product> entities) {
		productRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return productRepository.count();
	}

	@Override
	public <S extends Product> boolean exists(Example<S> example) {
		return productRepository.exists(example);
	}

	@Override
	public void deleteById(Integer id) {
		productRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		productRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Product entity) {
		productRepository.delete(entity);
	}

	@Override
	public <S extends Product, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return productRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		productRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		productRepository.deleteAllInBatch();
	}

	@Override
	public Product getOne(Integer id) {
		return productRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Product> entities) {
		productRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		productRepository.deleteAll();
	}

	@Override
	public Product getById(Integer id) {
		return productRepository.getById(id);
	}

	@Override
	public Product getReferenceById(Integer id) {
		return productRepository.getReferenceById(id);
	}

	@Override
	public <S extends Product> List<S> findAll(Example<S> example) {
		return productRepository.findAll(example);
	}

	@Override
	public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
		return productRepository.findAll(example, sort);
	}

	@Override
	public List<Product> findAllActive() {
		return productRepository.findAllActive();
	}

	@Override
	public Page<Product> findAllActive(Pageable pageable) {
		return productRepository.findAllActive(pageable);
	}

	@Override
	public List<Product> findAllActive(Sort sort) {
		return productRepository.findAllActive(sort);
	}

	@Override
	public List<Product> findAllByCategoryId(Integer id) {
		return productRepository.findAllByCategoryId(id);
	}

	@Override
	public void capNhatSoLuongton(Integer id, int quantity) {
		productRepository.updateInventory(id, quantity);
	}

	@Override
	public Page<HangBanChay> findTopSellingProducts(Pageable pageable) {
		return  productRepository.findTopSellingProducts(pageable) ;
	}

	@Override
	public Page<HangTon> findTopHangTonNhieuNhat(Pageable pageable) {
		return productRepository.findTopHangTonNhieuNhat(pageable);
	}

	@Override
	public Page<Product> findByNameContains(String name, Pageable pageable) {
		return productRepository.findByNameContains(name, pageable);
	}

}
