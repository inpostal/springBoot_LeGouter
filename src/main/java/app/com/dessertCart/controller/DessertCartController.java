package app.com.dessertCart.controller;

import app.com.dessertCart.entity.DessertCartDTO;
import app.com.dessertCart.service.DessertCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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


    @GetMapping("/dessertCart/{memberId}")
    public String getDessertCartByMemberId(@PathVariable Integer memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "/front-end/Dessert/DessertCart";
    }

    @GetMapping("/dessertCart/get/{memberId}")
    @ResponseBody
    public List<DessertCartDTO> getDessertCartByMemberId(@PathVariable Integer memberId) {
        List<DessertCartDTO> dessertCartDTOList = dessertCartService.getDessertCartByMemberId(memberId);
        return dessertCartDTOList;
    }

    @ResponseBody
    @PostMapping("/dessertCart/update")
    public DessertCartDTO updateDessertCartQuantity(@RequestParam("dessertId") Integer dessertId,
                                                    @RequestParam("memberId") Integer memberId,
                                                    @RequestParam("cartDessertQuantity") Integer cartDessertQuantity) {
        dessertCartService.updateDessertCartQuantity(dessertId, memberId, cartDessertQuantity);
        DessertCartDTO updatedDessertCart = dessertCartService.getDessertCartByDessertIdAndMemberId(dessertId, memberId);
        return updatedDessertCart;
    }


    @PostMapping("/dessertCart/delete")
    public String delete(@RequestParam("dessertId") Integer dessertId,
                         HttpSession session) {
//        Members member = (Members) session.getAttribute("user");
//        if (member != null) {
//            Integer memberId = member.getId();
//            dessertCartService.delete(dessertId, memberId);
//        }
        dessertCartService.delete(dessertId, 1);
        return "/front-end/Dessert/DessertCart";
    }
}