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

//團購活動明細

    @GetMapping("/group/activity/list")
    public String GroupActivityAll(){
        return "/back-end/groupOrder/groupActivity";
    }
    //thyleaf
    @GetMapping("/group/activity/update/front")
    public String updateGroupActivity(@RequestParam Integer groupActivityId, Model model){
        GroupActivity groupActivity = service.getGroupAcivityById(groupActivityId);
        model.addAttribute("groupActivity", groupActivity);
        return "/front-end/grouporder/groupOrderEmpSingle";

    }
//前台訂單主檔按鈕鍵(查看訂單活動)
    @GetMapping("/group/activity/update")
    public String updateGroupActivityBck(@RequestParam Integer groupActivityId, Model model){
        GroupActivity groupActivity = service.getGroupAcivityById(groupActivityId);
        model.addAttribute("groupActivity", groupActivity);
        return "/back-end/groupOrder/groupActivity";

    }


}
