package app.com.groupordermaster.controller;



import app.com.groupordermaster.service.GroupOrderBonusService;

import app.com.groupordermaster.vo.GroupActivity;
import app.com.groupordermaster.vo.GroupOrderBonusDTO;
import app.com.groupordermaster.vo.GroupOrderMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GroupOrderBonusController {

    @Autowired
    private GroupOrderBonusService service;


//後臺分潤列表
//thyleaf
    @GetMapping("/groupOrder/groupOrderBonus/getAll")
    public String getAllGroupOrderMasters( Model model) {
        List<GroupOrderBonusDTO> result = service.getAllGroupOrderMasters();
        model.addAttribute("groupOrderBonus", result);
        return "/back-end/groupOrder/groupOrderBonus";
    }
    //前台團購主查詢
    @GetMapping("/groupbonusfront")
    @ResponseBody
    public List<GroupOrderBonusDTO> getallbonusfront(@RequestParam Integer memberId){
        return service.frontlist(memberId);
    }

    //後台修改分潤狀態分潤列表)
    @PostMapping("/groupOrder/groupOrderBonus/update")
    public ResponseEntity<?>updatebonus(@RequestParam Integer groupOrderBonusStatus, @RequestParam Integer groupOrderId){
        service.updatebonus(groupOrderId,groupOrderBonusStatus);
        return ResponseEntity.ok("修改成功");
    }

}







