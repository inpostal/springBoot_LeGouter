package app.com.dessertOrders.controller;


import app.com.dessertOrders.entity.Orders;
import app.com.dessertOrders.entity.OrdersDTO;
import app.com.dessertOrders.service.OrdersService;
import app.com.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
    @Autowired
    private MemberService memberService;


    @GetMapping("/orders/list")
    public String getAllOrders() {
        return "/back-end/Dessert/DessertOrderManagement";
    }

    @GetMapping("/orders")
    @ResponseBody
    public List<OrdersDTO> getAll() {
        List<Orders> list = ordersService.getAllOrders();
        List<OrdersDTO> dtoList = new ArrayList<>();

        for (Orders o :
                list) {
            OrdersDTO dto = new OrdersDTO();
            dto.setOrderID(o.getOrderId());
            dto.setMemberAC(memberService.getMemberById(o.getMemId()).getMemberAccount());
            dto.setOrdersDate(o.getOrderTime());
            dto.setCpOrderTotal(o.getCpOrderTotal());
            dto.setOrderStatus(o.getOrderStatus());
            dtoList.add(dto);
        }
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


        Orders orders = ordersService.getOrdersById(ordersId);
        orders.setReceiverName(receiverName);
        orders.setReceiverAddress(receiverAddress);
        orders.setReceiverPhone(receiverPhone);
        orders.setOrderStatus(orderStatus);
        orders.setReceiverEmail(receiverEmail);
        ordersService.update(orders);
        return ResponseEntity.ok().body("修改成功");
    }


}

