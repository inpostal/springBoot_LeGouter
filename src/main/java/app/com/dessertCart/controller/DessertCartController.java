package app.com.dessertCart.controller;

import app.com.dessertCart.entity.DessertCart;
import app.com.dessertCart.repository.DessertCartRepository;
import app.com.dessertCart.service.DessertCartService;
import app.com.member.vo.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ClassName: DessertCartController
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/27 PM 05:22
 */

@Controller
public class DessertCartController {
    @Autowired
    private DessertCartService dessertCartService;

    @Autowired
    private DessertCartRepository dessertCartRepository;


    @GetMapping("/dessertCart/{memberId}")
    public String showCartByMemberId(@PathVariable int memberId, Model model) {
        Members member = new Members();
        member.setMemberId(memberId);
        List<DessertCart> dessertCartItems = dessertCartService.getDessertCartByMember(member);
        double totalPrice = dessertCartService.calculateTotalPrice(dessertCartItems);
        model.addAttribute("dessertCartItems", dessertCartItems);
        model.addAttribute("totalPrice", totalPrice);

        return "/front-end/Dessert/DessertCart";
    }

    @PostMapping("/dessertCart/updateQuantity")
    @ResponseBody
    public ResponseEntity<?> updateQuantity(@RequestParam("dessertCartId") int dessertCartId, @RequestParam("newQuantity") int newQuantity) {
        // 根據id查找購物車項目
        Optional<DessertCart> optionalDessertCart = dessertCartRepository.findById(dessertCartId);

        if (optionalDessertCart.isPresent()) {
            DessertCart dessertCart = optionalDessertCart.get();
            dessertCart.setCartDessertQuantity(newQuantity);
            double subtotal = dessertCart.getTotalPrice();

            dessertCartRepository.save(dessertCart);

            // 創建一個Map對象
            Map<String, Object> response = new HashMap<>();
            response.put("subtotal", subtotal);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
