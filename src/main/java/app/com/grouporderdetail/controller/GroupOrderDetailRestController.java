package app.com.grouporderdetail.controller;

import app.com.grouporderdetail.service.GroupOrderDetailService;
import app.com.grouporderdetail.vo.GroupOrderDetail;
import app.com.member.vo.Members;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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

    @GetMapping("/check/user/login")
    public Members checkUserLogin(HttpSession session){
        Members members=(Members)session.getAttribute("user");
        return members;
    }

}