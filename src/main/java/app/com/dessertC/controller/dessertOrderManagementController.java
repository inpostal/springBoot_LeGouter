package app.com.dessertC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ClassName: dessertOrderManagementController
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/20 PM 08:06
 */

@Controller
public class dessertOrderManagementController {
    @GetMapping("/dessert/orderManagement")
    public String orderManagement() {
        return "/front-end/Dessert/DessertOrders";
    }
}
