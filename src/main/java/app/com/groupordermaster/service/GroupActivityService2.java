package app.com.groupordermaster.service;

import app.com.groupordermaster.repository.GroupActivityRepository2;
import app.com.groupordermaster.vo.GroupActivity;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupActivityService2 {
    @Autowired
    private GroupActivityRepository2 repository;
    public List<GroupActivity>GroupActivityAll(){
        List<GroupActivity> list=repository.findAll();
        return  list;
    }
    public GroupActivity getGroupActivityById(Integer groupActivityId){
        Optional<GroupActivity>update=repository.findById(groupActivityId);
        return update.get();
    }



}
