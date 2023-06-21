package app.com.common;


import app.com.carousel.service.CarouselService;
import app.com.carousel.vo.Carousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ConponentController {
    @Autowired
    private CarouselService service;

    // 拿到後台導覽列
    @GetMapping("/get/navbar")
    public String getNavBar(){
        return "/back-end/Conponents/SideBar";
    }

    // 拿到後台頁首
    @GetMapping("/get/header")
    public String getHeader(){
        return "/back-end/Conponents/Header";
    }

    // 前往前台首頁
    @GetMapping("/LeGouter")
    public String about(Model model){
        List<Carousel> list = service.getAllCarousel();
        model.addAttribute("carousel",list);
        return "front-end/FrontIndex";
    }
}
