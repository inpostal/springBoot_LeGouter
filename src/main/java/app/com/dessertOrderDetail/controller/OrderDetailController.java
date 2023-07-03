package app.com.dessertOrderDetail.controller;

import app.com.dessertOrderDetail.entity.OrderDetailDTO;
import app.com.dessertOrderDetail.service.OrderDetailService;
import app.com.dessertOrders.entity.Orders;
import app.com.dessertOrders.service.OrdersService;
import app.com.emp.vo.Employee;
import app.com.member.vo.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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


//    @GetMapping("/orderDetail/get")
//    public String getBackEndOrderDetail(@RequestParam Integer orderId, Model model) {
//        return getOrderDetail(orderId, model, "/back-end/Dessert/DessertOrderDetail");
//    }
//
//    @GetMapping("/memOrderDetail/get")
//    public String getFrontEndOrderDetail(@RequestParam Integer orderId, Model model) {
//        return getOrderDetail(orderId, model, "/front-end/Dessert/DessertMemOrderDetail");
//    }
//
//    private String getOrderDetail(Integer orderId, Model model, String viewName) {
//        List<OrderDetailDTO> orderDetailDTOList = orderDetailService.getOrderDetailDTOList(orderId);
//        Orders orders = ordersService.getOrdersById(orderId);
//
//        model.addAttribute("orderDetailList", orderDetailDTOList);
//        model.addAttribute("orders", orders);
//        return viewName;
//    }

    @GetMapping("/orderDetail/get")
    public String getBackEndOrderDetail(@RequestParam Integer orderId, HttpSession session, Model model) {
        Employee emp = (Employee) session.getAttribute("emp");
        if (emp == null) {
            session.setAttribute("location", "/orders/list");
            return "redirect:/employee/login";
        }
        return getOrderDetail(orderId, model, "/back-end/Dessert/DessertOrderDetail");
    }

    @GetMapping("/memOrderDetail/get")
    public String getFrontEndOrderDetail(@RequestParam Integer orderId, HttpSession session, Model model) {
        Members member = (Members) session.getAttribute("user");
        if (member == null) {
            session.setAttribute("location", "/ordersMem/list");
            return "redirect:/login";
        }
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
