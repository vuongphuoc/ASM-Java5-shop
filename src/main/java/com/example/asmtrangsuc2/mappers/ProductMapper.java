package com.example.asmtrangsuc2.mappers;

import com.example.asmtrangsuc2.entities.Product;
import com.example.asmtrangsuc2.models.ProductModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ProductMapper {

	@Autowired
	private ModelMapper mapper;

	public Product convertToEntity(ProductModel productModel) {
		Product product = mapper.map(productModel, Product.class);
		return product;
	}

	public ProductModel convertToDTO(Product product) {
		ProductModel productModel = mapper.map(product, ProductModel.class);
		return productModel;
	}
}
