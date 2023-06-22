package app.com.dessertC.controller;


import app.com.dessertC.entity.Orders;
import app.com.dessertC.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


/**
 * 控制层
 *
 * @author makejava
 * @since 2023-06-21 10:35:13
 */
@Controller
public class OrdersController {

    @Autowired
    private OrdersService ordersService;


    @GetMapping("/orders/list")
    public String getAllOrders() {
        return "/back-end/Dessert/DessertOrderManagement";
    }

    @GetMapping("/orders")
    @ResponseBody
    public List<Orders> getAll() {
        List<Orders> list = ordersService.getAllOrders();
        return list;
    }

    @GetMapping("/orders/edit")
    public String editOrdersPage(@RequestParam Integer ordersId, Model model) {
        Orders orders = ordersService.getOrdersById(ordersId);
        model.addAttribute("orders", orders);
        return "/back-end/Dessert/DessertOrderEdit";
    }

    @PostMapping("/orders/update")
    public ResponseEntity<?> update(@RequestParam Integer ordersId,
                                    @RequestParam Integer orderTotal,
                                    @RequestParam Date orderTime,
                                    @RequestParam String receiverName,
                                    @RequestParam String receiverAddress,
                                    @RequestParam String receiverPhone,
                                    @RequestParam Integer orderStatus) {


        Orders orders = ordersService.getOrdersById(ordersId);
        orders.setReceiverName(receiverName);
        orders.setReceiverAddress(receiverAddress);
        orders.setReceiverPhone(receiverPhone);
        orders.setOrderStatus(orderStatus);
        ordersService.update(orders);
        return ResponseEntity.ok().body("修改成功");
    }
}

