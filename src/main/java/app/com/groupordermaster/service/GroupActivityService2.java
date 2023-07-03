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
//團購主檔選擇活動詳情
    public GroupActivity getGroupAcivityById(Integer groupActivityId){
        Optional<GroupActivity>update=repository.findById(groupActivityId);
        return update.get();
    }
    //修改團購主活動內容
    public void update(GroupActivity groupActivity){
        //去資料庫抓這個id的資料
        GroupActivity reference = repository.getReferenceById(groupActivity.getGroupActivityId());
        //將前台拿到的資料--->set到資料庫的資料
        reference.setGroupActivityContent(groupActivity.getGroupActivityContent());
        reference.setGroupName(groupActivity.getGroupName());
        //存回資料庫
        repository.save(reference);
    }
    }





