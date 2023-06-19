package app.com.groupordermaster.service;

import app.com.groupordermaster.repository.GroupOrderMasterRepository;

import app.com.groupordermaster.vo.GroupOrderMaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupOrderMasterService {
@Autowired
    private GroupOrderMasterRepository repository;
    public List<GroupOrderMaster>GroupOrderMasterAll(){
        List<GroupOrderMaster>groupOrderMaster=repository.findAll();
        return  groupOrderMaster;
    }
}
