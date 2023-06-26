package app.com.groupordermaster.controller;

import app.com.groupordermaster.service.GroupOrderMasterService;
import app.com.groupordermaster.vo.GroupOrderMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



import java.util.List;
@RestController
public class GroupOrderMasterRestController {
    @Autowired
    private GroupOrderMasterService service;
//數據
    @GetMapping("/group/order/master/restcontrollerlist")
    public List<GroupOrderMaster> getGroupOrderMasterAll() {
        List<GroupOrderMaster> list = service.GroupOrderMasterAll();
        return list;
    }
}
