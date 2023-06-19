package app.com.groupordermaster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//直接找html導致頁面

@Controller
public class GroupOrderMasterController {
    @GetMapping("/group/order/master")
    public String GroupOrderMasterAll() {
        return "/back-end/groupOrder/groupOrderMaster";
    }

}

