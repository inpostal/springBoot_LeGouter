package app.com.groupordermaster.controller;


import app.com.groupordermaster.service.GroupActivityService2;
import app.com.groupordermaster.service.GroupOrderMasterService;
import app.com.groupordermaster.vo.GroupActivity;
import app.com.groupordermaster.vo.GroupOrderBonusDTO;
import app.com.groupordermaster.vo.GroupOrderMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GroupOrderBonusController {

    @Autowired
    private GroupOrderMasterService service1;
    @Autowired
    private GroupActivityService2 service2;

//
    @GetMapping("/groupOrder/groupOrderBonus/getAll")
    public String getAllGroupOrderMasters( Model model) {
        List<GroupOrderMaster> groupOrderMaster = service1.GroupOrderMasterAll();
        List<GroupOrderBonusDTO> result = new ArrayList<>();

        for (GroupOrderMaster g :
                groupOrderMaster) {
            GroupOrderBonusDTO dto = new GroupOrderBonusDTO();
            GroupActivity groupActivity = service2.getGroupActivityById(g.getGroupActivityId());

            dto.setGroupOrderId(g.getGroupOrderId());
            dto.setActivityId(g.getGroupActivityId());
            dto.setMemberID(g.getMemId());
            dto.setGroupOrderStar(groupActivity.getGroupOrderStar());
            dto.setGroupOrderEnd(groupActivity.getGroupOrderEnd());
            dto.setTotalGroupProductPrice(g.getTotalGroupProductPrice());
            dto.setGroupOrderBonus(g.getGroupOrderBonus());

            switch (groupActivity.getGroupOrderMin()) {
                case 200:
                    dto.setBonusRate("8%");
                    break;
                case 400:
                    dto.setBonusRate("10%");
                    break;
                case 600:
                    dto.setBonusRate("12%");
                    break;
            }


            if (g.getGroupOrderBonusStatus() == 0) {
                dto.setGroupOrderBonusStatus("未發放");
            } else {
                dto.setGroupOrderBonusStatus("已發放");
            }
            result.add(dto);
        }


        model.addAttribute("groupOrderBonus", result);
        return "/back-end/groupOrder/groupOrderBonus";
    }
}







