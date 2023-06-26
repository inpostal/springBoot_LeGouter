package app.com.grouporderdetail.controller;

import app.com.grouporderdetail.service.GroupOrderDetailService;
import app.com.grouporderdetail.vo.GroupOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupOrderDetailRestController {
    @Autowired
    private GroupOrderDetailService service;
    @GetMapping("/group/order/detail/restcontrollerlist")
    public List<GroupOrderDetail> getAllGroupOrderDetaill(@RequestParam Integer groupOrderId){
        List<GroupOrderDetail>list=service.getGroupOrderDetailById(groupOrderId);
        return list;
    }
}