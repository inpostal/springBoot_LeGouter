package app.com.groupordermaster.repository;


import app.com.groupordermaster.vo.GroupOrderMaster;

import org.springframework.data.jpa.repository.JpaRepository;
//底層完成曾刪改查
public interface GroupOrderMasterRepository extends  JpaRepository<GroupOrderMaster,Integer> {

}
