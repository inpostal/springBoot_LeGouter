package app.com.CourseOrder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseOrderController {

    @GetMapping("/courseOrder/history")
    public String courseOrderHistory(){
        return "/front-end/CourseOrder/OrderHistory";
    }

    @GetMapping("/courseOrder/manage")
    public String courseOrderManage(){
        return "/back-end/CourseOrder/Orders";
    }

}
