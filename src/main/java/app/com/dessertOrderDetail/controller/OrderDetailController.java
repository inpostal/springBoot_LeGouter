package app.com.dessertOrderDetail.controller;

import app.com.dessertOrderDetail.entity.OrderDetailDTO;
import app.com.dessertOrderDetail.service.OrderDetailService;
import app.com.dessertOrders.entity.Orders;
import app.com.dessertOrders.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ClassName: OrderDetailController
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/24 AM 01:19
 */
@Controller
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrdersService ordersService;


    @GetMapping("/orderDetail/get")
    public String getBackEndOrderDetail(@RequestParam Integer orderId, Model model) {
        return getOrderDetail(orderId, model, "/back-end/Dessert/DessertOrderDetail");
    }

    @GetMapping("/memOrderDetail/get")
    public String getFrontEndOrderDetail(@RequestParam Integer orderId, Model model) {
        return getOrderDetail(orderId, model, "/front-end/Dessert/DessertMemOrderDetail");
    }

    private String getOrderDetail(Integer orderId, Model model, String viewName) {
        List<OrderDetailDTO> orderDetailDTOList = orderDetailService.getOrderDetailDTOList(orderId);
        Orders orders = ordersService.getOrdersById(orderId);

        model.addAttribute("orderDetailList", orderDetailDTOList);
        model.addAttribute("orders", orders);

        return viewName;
    }


}
