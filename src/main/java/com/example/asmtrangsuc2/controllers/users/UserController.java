package com.example.asmtrangsuc2.controllers.users;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.Random;


import com.example.asmtrangsuc2.entities.Account;
import com.example.asmtrangsuc2.entities.Order;
import com.example.asmtrangsuc2.entities.OrderDetail;
import com.example.asmtrangsuc2.mappers.AccountMapper;
import com.example.asmtrangsuc2.models.AccountModel;
import com.example.asmtrangsuc2.models.ProfileModel;
import com.example.asmtrangsuc2.services.IAccountService;
import com.example.asmtrangsuc2.services.IOrderService;
import com.example.asmtrangsuc2.utilities.EncryptUtils;
import com.example.asmtrangsuc2.utilities.UploadFileUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponentModule;
import org.springframework.data.repository.query.Param;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import net.bytebuddy.utility.RandomString;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private IAccountService accountService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    EncryptUtils encryptUtils;

    @Autowired
    private UploadFileUtils uploadFileUtils;
    @Autowired
    JavaMailSender javaMailSender;

    @GetMapping("login")
    public String loginView() {

        return "/user/login";
    }

    @PostMapping("login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        Account account = this.accountService.findByUsername(username);
//
//        if (account != null) {
//            session.setAttribute("account", account);
//
//            if (account.getAdmin() == 1) {
//                //  chuyen den trang voi quyen quan ly
//                return "redirect:/admin/home";
//            } else {
//                // chuyen den trang voi quyen khach hang
//                return "redirect:/home";
//            }
//        } else {
//            // Đăng nhập thất bại
//            session.setAttribute("errorPassword", "Sai tên tài khoản hoặc mật khẩu");
//            return "redirect:/login";
//        }
//
//    }

        if (account != null) {
            String passwordEncrypt = account.getPassword();

            boolean checkEncryptPassword = encryptUtils.checkPassword(password, passwordEncrypt);

            if (!checkEncryptPassword) {
                session.setAttribute("errorPassword", "Sai tên tài khoản hoặc mật khẩu");
                return "redirect:/login";
            } else {

                session.setAttribute("account", account);

                if (account.getAdmin() == 1) {
                    return "redirect:/admin/home";
                } else {
                    return "redirect:/home";
                }
            }

        } else {
            session.setAttribute("errorPassword", "Sai tên tài khoản hoặc mật khẩu");
            return "redirect:/login";
        }

    }

    @GetMapping("order-history")
    public String orderHistory(Model model) {

        Account account = (Account) session.getAttribute("account");

        System.out.println(account.getUsername());

        List<Order> listOrders = this.orderService.findAllByAccountId(account.getId());

        model.addAttribute("listOrders", listOrders);

        model.addAttribute("view", "/views/user/order-history.jsp");
        return "/user/index";
    }

    @GetMapping("order-detail/{id}")
    public String orderDetail(Model model, @PathVariable("id") Integer id) {

        Optional<Order> orderOptional = this.orderService.findById(id);

        if (!orderOptional.isPresent()) {
            session.setAttribute("errorOrder", "Hóa đơn không tồn tại");
            return "redirect:/order-history";
        }

        List<OrderDetail> listOrderDetails = orderOptional.get().getOrderDetails();

        session.removeAttribute("errorOrder");
        model.addAttribute("listOrderDetails", listOrderDetails);
        model.addAttribute("view", "/views/user/order-detail.jsp");
        return "/user/index";
    }

    @GetMapping("cancel-order/{id}")
    public String cancelOrder(Model model, @PathVariable("id") Integer id) {

        Optional<Order> order = this.orderService.findById(id);
        Account accountSession = (Account) session.getAttribute("account");

        if (!order.isPresent()) {
            session.setAttribute("errorOrder", "Đơn hàng không tồn tại");
            return "redirect:/order-history";
        }

        if (order.get().getStatus() != 0) {
            session.setAttribute("errorOrder", "Chỉ có thể hủy các đơn hàng có trạng thái là chờ xác nhận");
            if (order.get().getAccountById().getId() != accountSession.getId()) {
                session.setAttribute("errorOrder", "Đơn hàng không tồn tại");
            }
            return "redirect:/order-history";
        }

        order.get().setStatus(2);
        this.orderService.save(order.get());

        return "redirect:/order-history";
    }

    @GetMapping("change-password")
    public String getChangePasswordForm(Model model) {

        model.addAttribute("view", "/views/user/change-password.jsp");
        return "/user/index";
    }

    @PostMapping("change-password")
    public String changePassword(Model model, @RequestParam("old-password") String oldPassword,
                                 @RequestParam("new-password") String newPassword) {

        Account accountSession = (Account) session.getAttribute("account");
        Account account = this.accountService.getById(accountSession.getId());


        boolean passwordMatches = encryptUtils.checkPassword(oldPassword, accountSession.getPassword());

        if (!passwordMatches) {
            session.setAttribute    ("errorChangePassword", "Mật khẩu cũ không đúng");
            return "redirect:/change-password";
        }

        newPassword = this.encryptUtils.encrypt(newPassword);
        account.setPassword(newPassword);
//        account.setResetPasswordToken(newPassword);

        this.accountService.save(account);

        model.addAttribute("message", "Change password successfully");
        model.addAttribute("view", "/views/user/message.jsp");
        return "/user/index";
    }


    private void sendEmail(String email, String resetPasswordLink)
            throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("vuongphuoc2404@gmail.com", "Support");
        helper.setTo(email);

        String subject = "Đây là liên kết để đặt lại mật khẩu của bạn.";
        String content = "<p>Xin chào, </p>" + "<p>Bạn đã yêu cầu đặt lại mật khẩu</p>"
                + "<p>  Nhấp vào liên kết bên dưới để thay đổi mật khẩu của bạn:</p>" + "<a href=\"" + resetPasswordLink
                + "\">Change my password</a>";
        helper.setSubject(subject);
        helper.setText(content, true);

        this.javaMailSender.send(message);
    }

    @GetMapping("forgot-password")
    public String getFormForgotPassword(Model model) {

        model.addAttribute("view", "/views/user/forgot-password.jsp");
        return "/user/index";
    }

//    @GetMapping("/nhap-ma")
//    public String hienThiNhapMa() {
//        return "/user/check-code";
//    }
//
//    @GetMapping("/dat-lai-mat-khau")
//    public String nhapMatKhauMoi() {
//        return "/user/change-password";
//    }
//
//
//    @PostMapping("/gui-ma")
//    public String kiemtraEmail(@RequestParam("email") String email) {
//        // kiem tra email co tai khoan khong
//        Account account = this.accountService.findAccountByEmailEquals(email);
//        if (account == null) {
//            // email chua dang ki tai khoan -> chuyen ve trang nhap email
//            return "redirect:/forgot-password";
//        } else {
//
//            // luu accoun vao session
//            session.setAttribute("account", account);
//            // emmail da dang ki tai khaon-> gui code qua email, chuyen den trang nhap code
//            Random random = new Random();
//            int code = random.nextInt(900000) + 100000; // Tạo số ngẫu nhiên từ 100000 đến 999999
//
//            session.setAttribute("code", code);
//            // Gửi email chứa mã đến địa chỉ email
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setTo(email);
//            mailMessage.setSubject("Mã xác nhận quên mật khẩu");
//            mailMessage.setText("Mã xác nhận của bạn là: " + code);
//            javaMailSender.send(mailMessage);
//            return "redirect:/forgot-password/check-code";
//        }
//    }
//
//    @PostMapping("/xac-minh")
//    public String xacMinhCode(@RequestParam("code") int userCode,
//                              RedirectAttributes redirectAttributes) {
//        // lay ra code dung tu session
//        int code = (int) session.getAttribute("code");
//
//        // so sanh 2 code
//        if (code != userCode) {
//            // neu code khong dung-> gui thong bao, quay lai trang nhap code
//            redirectAttributes.addFlashAttribute("error", "Sai ma roi");
//            return "redirect:/forgot-password/check-code";
//
//        } else {
//            // neu dung-> chuyen trang nhap mat khau
//            return "redirect:/forgot-password/new-password";
//        }
//    }

//    @PostMapping("/update")
//    public String update(@ModelAttribute("accountemail") Account account,@RequestParam("newpass") String newpass,
//                         @RequestParam("repass") String repass,
//                         RedirectAttributes redirectAttributes
//    ) {
//        // kiem tra 2 mat khau trung nhau chua
//        if (newpass.equals(repass) == false) {
//            redirectAttributes.addFlashAttribute("error", "Sai ma roi");
//            return "redirect:/check-code/new-password";
//        } else {
//            this.accountService.save(account);
//            return "redirect:/login";
//        }
//    }


    ////////////////////////////////////////////////////
    @PostMapping("forgot-password")
    public String forgotPassword(Model model, @RequestParam("email") String email) {

        String token = RandomString.make(45);

        String url = this.request.getRequestURL().toString().replace(this.request.getServletPath(), "");

        try {
            this.accountService.updateResetPassword(token, email);

            String resetPasswordLink = url + "/reset-password?token=" + token;

            sendEmail(email, resetPasswordLink);

            session.setAttribute("mess", "Liên kết đặt lại mật khẩu đã được gửi đến email của bạn");
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("view", "/views/user/forgot-password.jsp");
        return "/user/index";
    }


    @GetMapping("reset-password")
    public String getFormResetPassword(Model model, @Param("token") String token) {

        Account account = this.accountService.get(token);

        if (account == null) {
            model.addAttribute("message", "Invalid token");
            model.addAttribute("view", "/views/user/message.jsp");
            return "/user/index";
        }

        model.addAttribute("token", token);
        model.addAttribute("view", "/views/user/reset-password.jsp");
        return "/user/index";
    }

    @PostMapping("reset-password")
    public String resetPassword(Model model, @RequestParam("password") String password,
                                @RequestParam("token") String token) {

        Account account = this.accountService.get(token);

        if (account == null) {
            model.addAttribute("message", "Invalid token");

        } else {
            this.accountService.updatePassword(account, password);
            model.addAttribute("message", "Change password successfully");
        }

        model.addAttribute("view", "/views/user/message.jsp");
        return "/user/index";
    }
    ////////////////////////////////////////////////////

    @GetMapping("profile")
    public String editProfile(Model model, @ModelAttribute("accountModel") ProfileModel accountModel) {

        Account account = (Account) session.getAttribute("account");

        model.addAttribute("account", account);
        model.addAttribute("view", "/views/user/profile.jsp");
        return "/user/index";
    }

    @PostMapping("update-profile")
    public String updateProfile(Model model, @Valid @ModelAttribute("accountModel") ProfileModel accountModel,
                                BindingResult result) {

        Account accountSession = (Account) session.getAttribute("account");
        Account account = this.accountService.getById(accountSession.getId());

        if (result.hasErrors()) {
            model.addAttribute("view", "/views/user/profile.jsp");
            return "/user/index";
        }

        if (!accountModel.getImageFile().isEmpty()) {
            account.setPhoto(uploadFileUtils.uploadFile(accountModel.getImageFile()));
        }

        account.setEmail(accountModel.getEmail());
        account.setFullName(accountModel.getFullname());
        account.setUsername(accountModel.getUsername());
        account.setAdmin(accountSession.getAdmin());
        account.setPassword(accountSession.getPassword());
        account.setActivated(accountSession.getActivated());

        this.accountService.save(account);

        session.setAttribute("account", account);

        return "redirect:/profile";
    }

    @GetMapping("logout")
    public String logout() {
        if (session != null) {
            session.removeAttribute("account");
        }
        return "redirect:/home";
    }

    @GetMapping("reg")
    public String create(@ModelAttribute("accountModel") AccountModel accountModel) {
        return "/user/register";
    }

    @PostMapping("dangky")
    public String store(@Valid @ModelAttribute("accountModel") AccountModel accountModel,
                           BindingResult result) {


        Account account = this.accountMapper.convertToEntity(accountModel);
        String passwordEncrypt = encryptUtils.encrypt(accountModel.getPassword());

        if (result.hasErrors()) {
            return "/user/register";
        }

        Account accountCheckUsername = this.accountService.findByUsername(accountModel.getUsername());
        Account accountCheckEmail = this.accountService.findByEmail(accountModel.getEmail());

        if (accountCheckUsername != null) {
            session.setAttribute("errorUsername", "Username đã tồn tại");
            return "/user/register";
        }

        if (accountCheckEmail != null) {
            session.setAttribute("errorEmail", "Email đã tồn tại");
            return "/user/register";
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
        return "redirect:/login";

        }



    }


