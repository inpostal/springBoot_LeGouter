package app.com.carousel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarouselController {

    @GetMapping("/carousel/manage")
    public String carouselManage() {
        return "/back-end/Carousel/Carousel";
    }

    @GetMapping("/carousel/edit")
    public String carouselEdit() {
        return "/back-end/Carousel/CarouselEdit";
    }
}
