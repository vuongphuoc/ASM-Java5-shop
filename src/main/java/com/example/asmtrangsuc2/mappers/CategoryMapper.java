package com.example.asmtrangsuc2.mappers;

import com.example.asmtrangsuc2.entities.Category;
import com.example.asmtrangsuc2.models.CategoryModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CategoryMapper {

	private ModelMapper mapper;

	public CategoryMapper(ModelMapper mapper) {
		this.mapper = mapper;
	}

	public Category convertToEntity(CategoryModel categoryModel) {
		Category category = mapper.map(categoryModel, Category.class);
		return category;
	}

	public CategoryModel convertToDTO(Category category) {
		CategoryModel categoryModel = mapper.map(category, CategoryModel.class);
		return categoryModel;
	}
}
