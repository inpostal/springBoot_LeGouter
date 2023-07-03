package app.com.grouporderdetail.service;
import app.com.grouporderdetail.repository.GroupOrderDetailRepository;
import app.com.grouporderdetail.vo.GroupOrderDetail;

import app.com.grouporderdetail.vo.GroupOrderDetailId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class GroupOrderDetailService {

    @Autowired
    private GroupOrderDetailRepository repository;
//取的所有id
    public List<GroupOrderDetail>getAllGroupOrderDetail(){
        List<GroupOrderDetail> all=repository.findAll();
        return all;
    }
    //取得單筆id-->訂單主檔點進來用grouporderid抓所有會員資料
    public List<GroupOrderDetail> getGroupOrderDetailById(Integer groupOrderId) {
        List<GroupOrderDetail> list = repository.findByGroupOrderId(groupOrderId);
        return list;
    }
//修改訂單明細內的個人資料
    public void update(GroupOrderDetail groupOrderDetail) {
        repository.save(groupOrderDetail);
    }
//用複合主鍵抓到資料
    public GroupOrderDetail getOrderdetail(Integer groupOrderId, Integer memId) {   //用複合主鍵取得資料
        GroupOrderDetailId groupOrderDetailId = new GroupOrderDetailId();
        groupOrderDetailId.setGroupOrderId(groupOrderId);
        groupOrderDetailId.setMemberId(memId);

        Optional<GroupOrderDetail> optional = repository.findById(groupOrderDetailId);
        GroupOrderDetail groupOrderDetail = null;
        if (optional.isPresent()) {
            groupOrderDetail = optional.get();
        } else {
            throw new RuntimeException("GroupOrderDetail(id=" + groupOrderDetailId + ")不存在");
        }
        return groupOrderDetail;
    }
    //前台資料查詢個人訂單明細,用memberid去找自己的訂單明細
    public List<GroupOrderDetail> getAllByMemberId(Integer memberId) {
        List<GroupOrderDetail> list = repository.findAllByMemberId(memberId);
        return list;

  }

}

