package app.com.dessertOrders.controller;


import app.com.dessertOrders.entity.Orders;
import app.com.dessertOrders.entity.OrdersDTO;
import app.com.dessertOrders.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 控制層
 *
 * @author Charlie
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
    public List<OrdersDTO> getAll() {
        List<OrdersDTO> dtoList = ordersService.getAllOrdersDTO();
        return dtoList;
    }

    @GetMapping("/orders/edit")
    public String editOrdersPage(@RequestParam Integer ordersId, Model model) {
        Orders orders = ordersService.getOrdersById(ordersId);
        model.addAttribute("orders", orders);
        return "/back-end/Dessert/DessertOrderEdit";
    }

    @PostMapping("/orders/update")
    public ResponseEntity<?> update(@RequestParam Integer ordersId,
                                    @RequestParam String receiverName,
                                    @RequestParam String receiverAddress,
                                    @RequestParam String receiverPhone,
                                    @RequestParam Integer orderStatus,
                                    @RequestParam String receiverEmail) {


        ordersService.updateOrders(ordersId, receiverName, receiverAddress, receiverPhone, orderStatus, receiverEmail);
        return ResponseEntity.ok().body("修改成功");
    }


}

