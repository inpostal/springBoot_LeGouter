package app.com.dessertCart.controller;

import app.com.dessertCart.entity.DessertCartDTO;
import app.com.dessertCart.service.DessertCartService;
import app.com.member.repository.MemberRepository;
import app.com.member.vo.Members;
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
    @Autowired
    private MemberRepository memberRepository;


//    @GetMapping("/dessertCart/{memberId}")
//    public String getDessertCartByMemberId(@PathVariable Integer memberId, Model model) {
//        model.addAttribute("memberId", memberId);
//        return "/front-end/Dessert/DessertCart";
//    }
//
//
//    @GetMapping("/dessertCart/get/{memberId}")
//    @ResponseBody
//    public List<DessertCartDTO> getDessertCartByMemberId(@PathVariable Integer memberId) {
//        List<DessertCartDTO> dessertCartDTOList = dessertCartService.getDessertCartByMemberId(memberId);
//        return dessertCartDTOList;
//    }

    @GetMapping("/dessertCart/{memberId}")
    public String getDessertCartByMemberId(@PathVariable Integer memberId, Model model) {
        return getDessertCart(memberId, model, "/front-end/Dessert/DessertCart");
    }

    @GetMapping("/dessertCart/checkOut/{memberId}")
    public String getDessertCartCheckOut(@PathVariable Integer memberId, Model model) {
        return getDessertCart(memberId, model, "/front-end/Dessert/DessertCheckOut");
    }

    private String getDessertCart(Integer memberId, Model model, String viewName) {
        List<DessertCartDTO> dessertCartDTOList = dessertCartService.getDessertCartByMemberId(memberId);

        // Calculate the total
        int total = 0;
        for (DessertCartDTO item : dessertCartDTOList) {
            total += item.getSubtotalAmount();
        }
        int shippingCost = total > 500 ? 0 : 100;

        // Add total to the model
        model.addAttribute("total", total);
        model.addAttribute("shippingCost", shippingCost);
        model.addAttribute("dessertCartList", dessertCartDTOList);
        model.addAttribute("memberId", memberId);
        return viewName;
    }


    @ResponseBody
    @GetMapping("/dessertCart/data/{memberId}")
    public List<DessertCartDTO> getDessertCartData(@PathVariable Integer memberId) {
        return dessertCartService.getDessertCartByMemberId(memberId);
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


    @PostMapping("/submitOrder")
    public String submitOrder(HttpSession session) {
        Members member = (Members) session.getAttribute("user");
        if (member != null) {
            dessertCartService.submitOrder(member.getMemberId());
        }
        return "/front-end/Dessert/OrderSuccessful";
    }
}
