package app.com.groupordermaster.controller;

import app.com.groupordermaster.service.GroupActivityService2;
import app.com.groupordermaster.service.GroupOrderMasterService;
import app.com.groupordermaster.vo.GroupOrderMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class
GroupOrderBonusRestController {
    @Autowired
    private GroupOrderMasterService service1;
    @Autowired
    private GroupActivityService2 service2;

    @GetMapping("/group/order/master/restcontrollerlist")
    public List<GroupOrderMaster> getGroupOrderMasterAll() {
        List<GroupOrderMaster> list = service1.GroupOrderMasterAll();
        return list;
    }
    @GetMapping("/group/order/master/restcontrollerlist")
    public List<GroupOrderMaster> getGroupActivityAll() {
        List<GroupOrderMaster> list = service1.GroupOrderMasterAll();
        return list;
}

}
