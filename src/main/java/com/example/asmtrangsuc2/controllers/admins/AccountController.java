package com.example.asmtrangsuc2.controllers.admins;


import com.example.asmtrangsuc2.entities.Account;
import com.example.asmtrangsuc2.mappers.AccountMapper;
import com.example.asmtrangsuc2.models.AccountModel;
import com.example.asmtrangsuc2.services.IAccountService;
import com.example.asmtrangsuc2.utilities.EncryptUtils;
import com.example.asmtrangsuc2.utilities.UploadFileUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping("/admin/account")
public class AccountController {

	@Autowired
	private IAccountService accountService;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private UploadFileUtils uploadFileUtils;

	@Autowired
	private EncryptUtils encryptUtils;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;

	@GetMapping("index")
	public String index(Model model, @RequestParam(name = "p", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size) {

		String sortBy = this.request.getParameter("sort_by");
		Sort sort = Sort.by(Direction.ASC, "id");
		Page<Account> pageData = null;
		List<Account> listAccounts;

		if (sortBy != null) {

			sort = Sort.by(Direction.ASC, sortBy);

			listAccounts = this.accountService.findAllActive(sort);

		} else {
			listAccounts = this.accountService.findAllActive();
		}

		Pageable pageable = PageRequest.of(page, size, sort);
		pageData = this.accountService.findAllActive(pageable);

		model.addAttribute("listAccounts", pageData);
		model.addAttribute("sortBy", sortBy);

		return "/admin/account/index";
	}

	@GetMapping("create")
	public String create(@ModelAttribute("accountModel") AccountModel accountModel) {
		return "/admin/account/create";
	}

	@PostMapping("store")
	public String store(@Valid AccountModel accountModel, BindingResult result) {

		Account account = this.accountMapper.convertToEntity(accountModel);
		String passwordEncrypt = encryptUtils.encrypt(accountModel.getPassword());

		if (result.hasErrors()) {
			return "/admin/account/create";
		}
		
		Account accountCheckUsername = this.accountService.findByUsername(accountModel.getUsername());
		Account accountCheckEmail = this.accountService.findByEmail(accountModel.getEmail());

		if (accountCheckUsername != null) {
			session.setAttribute("errorUsername", "Username đã tồn tại");
			return "/admin/account/create";
		}
		
		if (accountCheckEmail != null) {
			session.setAttribute("errorEmail", "Email đã tồn tại");
			return "/admin/account/create";
		}

		if (!accountModel.getImageFile().isEmpty()) {
			account.setPhoto(uploadFileUtils.uploadFile(accountModel.getImageFile()));
		}

		account.setActivated(accountModel.getActivated());
		account.setAdmin(accountModel.getAdmin());
		account.setEmail(accountModel.getEmail());
		account.setFullName(accountModel.getFullname());
		account.setPassword(accountModel.getPassword());
		account.setUsername(accountModel.getUsername());
		account.setResetPasswordToken(passwordEncrypt);

		this.accountService.save(account);

		return "redirect:/admin/account/index";
	}




	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Account account) {
		AccountModel accountModel = this.accountMapper.convertToDTO(account);
		model.addAttribute("accountModel", accountModel);
		return "/admin/account/edit";
	}

	@PostMapping("update/{id}")
	public String update(@PathVariable("id") Integer id, @Valid AccountModel accountModel, BindingResult result) {

		Account account = this.accountService.getById(id);
		String oldPassword = account.getPassword();

		if (result.hasErrors()) {
			System.err.println(result.getAllErrors());
			return "/admin/account/edit";
		}
		
		if (!accountModel.getImageFile().isEmpty()) {
			account.setPhoto(uploadFileUtils.uploadFile(accountModel.getImageFile()));
		}
		
		accountModel.setPassword(oldPassword);

		account.setActivated(accountModel.getActivated());
		account.setAdmin(accountModel.getAdmin());
		account.setEmail(accountModel.getEmail());
		account.setFullName(accountModel.getFullname());
		account.setPassword(accountModel.getPassword());
		account.setUsername(accountModel.getUsername());
		account.setResetPasswordToken(oldPassword);

		this.accountService.save(account);

		return "redirect:/admin/account/index";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id) {

		Account account = this.accountService.getById(id);

		account.setActivated(0);

		this.accountService.save(account);

		return "redirect:/admin/account/index";
	}


}
