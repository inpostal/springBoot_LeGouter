package app.com.carousel.controller;

import app.com.carousel.service.CarouselService;
import app.com.carousel.vo.Carousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class CarouselController {
    @Autowired
    private CarouselService service;

    // 查看圖片網址
    @GetMapping("/carousel/view/{carId}")
    public ResponseEntity<?> viewCarousel(@PathVariable Integer carId) {
        Carousel carousel = service.getCarousel(carId);
        byte[] picture = carousel.getCarPicPic();
        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.IMAGE_GIF) // or another appropriate media type
                .body(picture);
    }

    // 輪播圖管理網址
    @GetMapping("/carousel/manage")
    public String carouselManage(HttpSession session, Model model) {
        if (session.getAttribute("emp") != null){
            List<Carousel> list = service.getAllCarousel();
            model.addAttribute("carouselList", list);
            return "/back-end/Carousel/Carousel";
        }else {
            return "redirect:/employee/login";
        }
    }

    // 輪播圖編輯網址
    @GetMapping("/carousel/edit/{carId}")
    public String carouselEdit(@PathVariable Integer carId, Model model) {
        Carousel carousel = service.getCarousel(carId);
        model.addAttribute("carousel", carousel);
        return "/back-end/Carousel/CarouselEdit";
    }

    // 輪播圖刪除請求
    @DeleteMapping("/carousel/delete/{carId}")
    public ResponseEntity<?> deleteCarousel(@PathVariable Integer carId) {
        List<Carousel> list = service.getAllCarousel();
        if (list.size() <= 1) {
            return ResponseEntity.badRequest().body("輪播圖至少要有一張");
        }else {
            service.deleteCarousel(carId);
            return ResponseEntity.ok().body("刪除成功");
        }
    }

    // 新增輪播圖請求
    @PostMapping("/carousel/add")
    @ResponseBody
    public Map<String, Boolean> addCarousel(@RequestParam("tag") String tag,
                                            @RequestParam("title") String title,
                                            @RequestParam("content") String content,
                                            @RequestParam("link_title") String linkTitle,
                                            @RequestParam("link") String link,
                                            @RequestParam("image") MultipartFile image) {

        Carousel carousel = new Carousel();
        carousel.setCarTag(tag);
        carousel.setCarTitle(title);
        carousel.setCarPicContent(content);
        carousel.setCarPicLinkTitle(linkTitle);
        carousel.setCarPicLink(link);

        try {
            carousel.setCarPicPic(image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        service.addCarousel(carousel);
        return Map.of("result", true);
    }

    // 輪播圖修改請求
    @PostMapping("/carousel/update")
    @ResponseBody
    public Map<String, Boolean> updateCarousel(@RequestParam("carId") Integer carId,
                                               @RequestParam("tag") String tag,
                                               @RequestParam("title") String title,
                                               @RequestParam("content") String content,
                                               @RequestParam("linkTitle") String linkTitle,
                                               @RequestParam("link") String link,
                                               @RequestParam("image") MultipartFile image) {

        Carousel carousel = service.getCarousel(carId);
        carousel.setCarTag(tag);
        carousel.setCarTitle(title);
        carousel.setCarPicContent(content);
        carousel.setCarPicLinkTitle(linkTitle);
        carousel.setCarPicLink(link);

        try {
            carousel.setCarPicPic(image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        service.updateCarousel(carousel);
        return Map.of("result", true);
    }

}
