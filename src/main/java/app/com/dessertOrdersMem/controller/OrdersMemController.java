package app.com.dessertOrdersMem.controller;


import app.com.dessertOrderDetail.entity.OrderDetailDTO;
import app.com.dessertOrderDetail.service.impl.OrderDetailServiceImpl;
import app.com.dessertOrdersMem.entity.OrdersMem;
import app.com.dessertOrdersMem.service.OrdersMemService;
import app.com.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;


/**
 * 控制層
 *
 * @author Charlie
 * @since 2023-06-21 10:35:13
 */
@Controller
public class OrdersMemController {

    @Autowired
    private OrdersMemService ordersMemService;
    @Autowired
    private MemberRepository memberRepository; // 替换为您的会员存储库
    @Autowired
    private OrderDetailServiceImpl orderDetailService;


    @GetMapping("/ordersMem/list/{memId}")
    public String getAllOrdersByMemberId(@PathVariable Integer memId, Model model) {
        List<OrdersMem> orders = ordersMemService.getAllOrdersByMemberId(memId);
        model.addAttribute("orders", orders);
        String memAccount = ordersMemService.getMemberAccountById(memId);
        model.addAttribute("memAccount", memAccount);


        List<OrderDetailDTO> dessertDetailsList = new ArrayList<>();
        for (OrdersMem order : orders) {
            List<OrderDetailDTO> dessertDetails = ordersMemService.getDessertDetails(order.getOrderId());
            dessertDetailsList.addAll(dessertDetails);
        }
        model.addAttribute("dessertDetailsList", dessertDetailsList);

        return "/front-end/Dessert/DessertOrdersMem";
    }


}



