package app.com.dessertOrdersMem.controller;


import app.com.dessertOrderDetail.entity.OrderDetailDTO;
import app.com.dessertOrdersMem.entity.OrdersMem;
import app.com.dessertOrdersMem.service.OrdersMemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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


    @GetMapping("/ordersMem/list/{memId}")
    public String getAllOrdersByMemberId(@PathVariable Integer memId, Model model) {
        List<OrdersMem> orders = ordersMemService.getAllOrdersByMemberId(memId);
        model.addAttribute("orders", orders);
        String memName = ordersMemService.getMemberAccountById(memId);
        model.addAttribute("memName", memName);

        Map<Integer, List<OrderDetailDTO>> dessertDetailsMap = new HashMap<>();
        for (OrdersMem order : orders) {
            List<OrderDetailDTO> dessertDetails = ordersMemService.getDessertDetails(order.getOrderId());
            dessertDetailsMap.put(order.getOrderId(), dessertDetails);
        }
        model.addAttribute("dessertDetailsMap", dessertDetailsMap);

        return "/front-end/Dessert/DessertOrdersMem";
    }


}




