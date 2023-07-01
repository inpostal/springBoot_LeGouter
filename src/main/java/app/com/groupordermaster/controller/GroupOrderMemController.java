package app.com.groupordermaster.controller;

import app.com.course.vo.Chef;
import app.com.groupordermaster.service.GroupOrderMemService;
import app.com.groupordermaster.service.GroupOrderMemSingleService;
import app.com.groupordermaster.vo.GroupActivity;
import app.com.groupordermaster.vo.GroupOrderMemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GroupOrderMemController {
    @Autowired
    private GroupOrderMemService service;
    @Autowired
    private GroupOrderMemSingleService service2;
    //團購主專區--團購查詢

    @GetMapping("/groupOrder/groupOrderMem/single")
    @ResponseBody
    public List<GroupOrderMemDTO> singleMemGroupOrder(@RequestParam Integer memberID) {
        return service.getAllGroupMem(memberID);
    }


    // 團購主專區--團購查詢--單一內容查詢
    //thyleaf團購主查詢單筆詳細活動
    @GetMapping("/groupOrder/groupOrderMem/single/detail")
    public String MemGroupOrderSingle(@RequestParam Integer groupActivityId, @RequestParam Integer groupStatus, Model model) {
        GroupOrderMemDTO groupActivity = service2.getGroupOrderMemSingle(groupActivityId);
        System.out.println(groupActivity);
        model.addAttribute("groupActivity", groupActivity);
        return "/front-end/grouporder/groupOrderEmpSingle";
    }




}
