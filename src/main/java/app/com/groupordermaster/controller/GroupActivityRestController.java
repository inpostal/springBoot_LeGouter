package app.com.groupordermaster.controller;

import app.com.groupordermaster.service.GroupActivityService2;
import app.com.groupordermaster.vo.GroupActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
@RestController
public class GroupActivityRestController {
    @Autowired
    private GroupActivityService2 service;
    @GetMapping("/group/activity/list2")
    public List<GroupActivity> GroupActivityAll(){
        List<GroupActivity> list=service.GroupActivityAll();
        return  list;
    }

}