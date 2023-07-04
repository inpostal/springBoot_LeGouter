package app.com.dessertCart.controller;

import app.com.dessertCart.entity.DessertCartDTO;
import app.com.dessertCart.entity.OrderInfo;
import app.com.dessertCart.service.DessertCartService;
import app.com.dessertCart.service.PaymentService;
import app.com.dessertOrders.repository.OrdersRepository;
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
    private PaymentService paymentService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private OrdersRepository ordersRepository;


    @GetMapping("/dessertCart")
    public String getDessertCartByMemberId(HttpSession session, Model model) {
        Members member = (Members) session.getAttribute("user");
        if (member == null) {
            session.setAttribute("location", "/dessertCart");
            return "redirect:/login";
        }
        Integer memberId = member.getMemberId();

        return getDessertCart(memberId, model, "/front-end/Dessert/DessertCart");
    }

    @GetMapping("/dessertCart/checkOut")
    public String getDessertCartCheckOut(HttpSession session, Model model) {
        Members member = (Members) session.getAttribute("user");
        if (member == null) {
            session.setAttribute("location", "/dessertCart/checkOut");
            return "redirect:/login";
        }
        Integer memberId = member.getMemberId();

        return getDessertCart(memberId, model, "/front-end/Dessert/DessertCheckOut");
    }

    private String getDessertCart(Integer memberId, Model model, String viewName) {
        List<DessertCartDTO> dessertCartDTOList = dessertCartService.getDessertCartByMemberId(memberId);

        // 檢查購物車是否為空
        if (dessertCartDTOList.isEmpty()) {
            model.addAttribute("isCartEmpty", true);
        }
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


    //    @PostMapping("/dessertCart/delete")
//    public String delete(@RequestParam("dessertId") Integer dessertId,
//                         HttpSession session) {
////        Members member = (Members) session.getAttribute("user");
////        if (member != null) {
////            Integer memberId = member.getId();
////            dessertCartService.delete(dessertId, memberId);
////        }
//        dessertCartService.delete(dessertId, 1);
//        return "/front-end/Dessert/DessertCart";
//    }
//
//
//    @PostMapping("/submitOrder")
//    public String submitOrder(@RequestBody OrderInfo orderInfo, HttpSession session) {
////        Members member = (Members) session.getAttribute("user");
////        if (member != null) {
//        Integer memberId = 1;
//        dessertCartService.submitOrder(memberId, orderInfo);
////        }
//        return "/front-end/Dessert/OrderSuccessful";
//    }
    @PostMapping("/dessertCart/delete")
    public String delete(@RequestParam("dessertId") Integer dessertId, HttpSession session) {
        Members member = (Members) session.getAttribute("user");
        if (member == null) {
            session.setAttribute("location", "/dessertCart/delete");
            return "redirect:/login";
        }
        Integer memberId = member.getMemberId();
        dessertCartService.delete(dessertId, memberId);
        return "/front-end/Dessert/DessertCart";
    }

    @ResponseBody
    @PostMapping("/submitOrder")
    public String submitOrder(@RequestBody OrderInfo orderInfo, HttpSession session) {
        Members member = (Members) session.getAttribute("user");
        if (member == null) {
            session.setAttribute("location", "/submitOrder");
            return "redirect:/login";
        }
        Integer memberId = member.getMemberId();
        String result = dessertCartService.submitOrder(memberId, orderInfo);


        // return "/front-end/Dessert/OrderSuccessful";
        return result;

    }

    @ResponseBody
    @PostMapping("/ecpay")
    public String ecpay(@RequestParam Integer orderId) {

        String result = paymentService.genAioCheckOutALL(orderId);
        return result;
//        /return "redirect:" + result;

    }


    @PostMapping("/OrderSuccessful")
    public String orderSuccessfulPage() {


        return "/front-end/Dessert/OrderSuccessful";
    }


}

