package com.example.asmtrangsuc2.controllers.admins;

import java.util.List;


import com.example.asmtrangsuc2.entities.Category;
import com.example.asmtrangsuc2.mappers.CategoryMapper;
import com.example.asmtrangsuc2.models.CategoryModel;
import com.example.asmtrangsuc2.services.ICategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/admin/category")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private CategoryMapper mapper;

	@Autowired
	private HttpServletRequest request;

	@GetMapping("index")
	public String index(Model model, @RequestParam(name = "p", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size) {

		String sortBy = this.request.getParameter("sort_by");
		Sort sort = Sort.by(Direction.ASC, "id");
		Page<Category> pageData = null;
		List<Category> lisCategories;

		if (sortBy != null) {

			sort = Sort.by(Direction.ASC, sortBy);

			lisCategories = this.categoryService.findAllActive(sort);

		} else {
			lisCategories = this.categoryService.findAllActive();
		}

		Pageable pageable = PageRequest.of(page, size, sort);
		pageData = this.categoryService.findAllActive(pageable);

		model.addAttribute("listCategories", pageData);
		model.addAttribute("sortBy", sortBy);

		return "/admin/category/index";
	}

	@GetMapping("create")
	public String create(@ModelAttribute("categoryModel") CategoryModel categoryModel) {
		return "/admin/category/create";
	}

	@PostMapping("store")
	public String store(@Valid CategoryModel categoryModel, BindingResult result) {

		Category category = this.mapper.convertToEntity(categoryModel);

		if (result.hasErrors()) {
			return "/admin/category/create";
		}

		category.setName(categoryModel.getName());
		category.setStatus(1);

		this.categoryService.save(category);

		return "redirect:/admin/category/index";
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Category category) {

		CategoryModel categoryModel = this.mapper.convertToDTO(category);
		model.addAttribute("categoryModel", categoryModel);

		return "/admin/category/edit";
	}

	@PostMapping("update/{id}")
	public String update(Model model, @PathVariable("id") Integer id, @Valid CategoryModel categoryModel,
			BindingResult result) {

		Category category = this.categoryService.getById(id);

		if (result.hasErrors()) {
			return "/admin/category/edit";
		}

		category.setName(categoryModel.getName());

		this.categoryService.save(category);

		return "redirect:/admin/category/index";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id) {

		Category category = this.categoryService.getById(id);

		category.setStatus(0);

		this.categoryService.save(category);

		return "redirect:/admin/category/index";
	}
}
