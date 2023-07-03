package app.com.groupordermaster.controller;

import app.com.groupordermaster.service.GroupOrderMasterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//直接找html導致頁面

@Controller
public class GroupOrderMasterController {
    @Autowired
    private GroupOrderMasterService service;
    //查看訂單主檔thyleaf
    @GetMapping("/groupordermaster/list")
    public String GroupOrderMasterAll() {return "/back-end/groupOrder/groupOrderMaster";
    }

//    @PostMapping("/groupMaster/update/numberofproduct")
//    public void updateNumberOfProduct(@RequestBody TotalAmountVO totalAmountVO) {
//        double totalAmount = totalAmountVO.getTotalAmount();
//        service.updateNumberOfProduct(totalAmount);
//    }
}

