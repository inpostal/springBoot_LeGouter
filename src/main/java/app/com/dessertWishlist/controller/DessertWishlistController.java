package app.com.dessertWishlist.controller;

import app.com.dessert5.service.DessertService;
import app.com.dessertWishlist.service.DessertWishlistService;
import app.com.dessertWishlist.vo.DessertWishlistDTO;
import app.com.member.service.MemberService;
import app.com.member.vo.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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

    @PostMapping("/deletewishlist")
    public ResponseEntity<String> deleteWishlist(HttpSession session,
                                                 @RequestParam Integer dessertId) {
        Members member = (Members) session.getAttribute("user");
        Integer memberId = member.getMemberId();

        try {
            dessertWishlistService.delete(dessertId, memberId);
            return new ResponseEntity<>("已成功刪除追蹤清單", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addtocart")
    public ResponseEntity<String> addToCart(HttpSession session,
                                            @RequestParam Integer dessertId) {
        Members member = (Members) session.getAttribute("user");
        Integer memberId = member.getMemberId();

        try {
            dessertWishlistService.addToCart(dessertId, memberId);
            return new ResponseEntity<>("已成功加入購物車", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/dessertwishlist")
    public String getDessertWishlist(HttpSession session, Model model) {
        Members member = (Members) session.getAttribute("user");
        if (member == null) {
            return "redirect:/login";
        }
        Integer memberId = member.getMemberId();
        List<DessertWishlistDTO> dtos = dessertWishlistService.getWishlist(memberId);
        model.addAttribute("wishlist", dtos);
        return "/front-end/Dessert/DessertWishlist";
    }

}
