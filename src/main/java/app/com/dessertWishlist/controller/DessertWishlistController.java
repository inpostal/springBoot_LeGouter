package app.com.dessertWishlist.controller;

import app.com.dessert5.service.DessertService;
import app.com.dessertWishlist.service.DessertWishlistService;
import app.com.dessertWishlist.vo.DessertWishlistDTO;
import app.com.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DessertWishlistController {

    @Autowired
    private DessertWishlistService dessertWishlistService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private DessertService dessertService;

    @PostMapping("/addWishlist")
    public ResponseEntity<String> addWishlist(@RequestParam Integer memberId,
                                              @RequestParam Integer dessertId) {
        try {
            dessertWishlistService.addWishlist(memberId, dessertId);
            return new ResponseEntity<>("已成功加入追蹤清單", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/removeWishlist")
    public ResponseEntity<String> removeWishlist(@RequestParam Integer memberId, @RequestParam Integer dessertId) {
        try {
            dessertWishlistService.removeWishlist(memberId, dessertId);
            return new ResponseEntity<>("已成功移出追蹤清單", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/dessertwishlist")
    public String getDessertWishlist(@RequestParam Integer memberId, Model model) {
        List<DessertWishlistDTO> dtos = dessertWishlistService.getWishlist(memberId);
        model.addAttribute("wishlist", dtos);
        return "/front-end/Dessert/DessertWishlist"; // 返回 DessertWishlist.html 頁面
    }

}
