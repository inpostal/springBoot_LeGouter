package app.com.groupordermaster.service;

import app.com.groupordermaster.repository.GroupOrderMasterRepository;

import app.com.groupordermaster.vo.GroupOrderMaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
@Service
public class GroupOrderMasterService {
@Autowired
    private GroupOrderMasterRepository repository;
    public List<GroupOrderMaster>GroupOrderMasterAll(){
        List<GroupOrderMaster>list=repository.findAll();
        return  list;
    }
//    public GroupOrderMaster getGroupOrderMasterById(Integer groupOrderMasterId){
//        GroupOrderMaster update=repository.findById(groupOrderMasterId).get();
//        return update;
//    }

//    public void updateNumberOfProduct(double totalAmount) {
//        GroupOrderMaster groupMaster = repository.findById(1L); // 假设根据某个唯一标识获取 groupMaster
//        groupMaster.setNumberOfProduct(totalAmount); // 更新 groupMaster 的 numberOfProduct
//        repository.save(groupMaster); // 保存更新后的 groupMaster
//    }
    }

