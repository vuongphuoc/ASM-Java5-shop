package com.example.asmtrangsuc2.controllers.admins;

import com.example.asmtrangsuc2.models.HangBanChay;
import com.example.asmtrangsuc2.models.HangTon;
import com.example.asmtrangsuc2.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/statistical")
public class ThongKeController {

    @Autowired
    IProductService productService;

    @GetMapping("index")
    public String top10SanPhamBanChayNhat(Model model) {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<HangBanChay> hangBanChayList = productService.findTopSellingProducts(pageRequest);
        model.addAttribute("hangBanChayList", hangBanChayList);
        Page<HangTon> HangE = productService.findTopHangTonNhieuNhat(pageRequest);
        model.addAttribute("HangE", HangE);
        return "/admin/statistical/thong-ke-bieu-do";
    }

    @GetMapping("tonkho")
    public String topHangE(Model model) {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<HangTon> HangE = productService.findTopHangTonNhieuNhat(pageRequest);
        model.addAttribute("HangE", HangE);
        return "/admin/statistical/hang-ton";
    }


}
