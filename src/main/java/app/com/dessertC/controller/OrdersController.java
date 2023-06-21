package app.com.dessertC.controller;


import app.com.dessertC.entity.Orders;
import app.com.dessertC.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/orders/getAll")
    @ResponseBody
    public List<Orders> getAll() {
        List<Orders> list = ordersService.getAllOrders();
        return list;
    }

}

