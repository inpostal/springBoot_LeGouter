package app.com.groupordermaster.controller;

import app.com.groupordermaster.service.GroupOrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//直接找html導致頁面

@Controller
public class GroupOrderMasterController {
    @Autowired
    private GroupOrderMasterService service;
    @GetMapping("/groupordermaster/list")
    public String GroupOrderMasterAll() {return "/back-end/groupOrder/groupOrderMaster";
    }
}

