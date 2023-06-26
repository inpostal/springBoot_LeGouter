package app.com.groupordermaster.controller;

import app.com.groupordermaster.service.GroupActivityService2;
import app.com.groupordermaster.vo.GroupActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GroupActivityController {
    @Autowired
    private GroupActivityService2 service;
    @GetMapping("/group/activity/list")
    public String GroupActivityAll(){
        return "/back-end/groupOrder/groupActivity";
    }
    //thyleaf
    @GetMapping("/group/activity/update")
    public String updateGroupActivity(@RequestParam Integer groupActivityId, Model model){
        GroupActivity groupActivity = service.getGroupActivityById(groupActivityId);
        model.addAttribute("groupActivity", groupActivity);
        return "/back-end/groupOrder/groupActivity";

    }

}
