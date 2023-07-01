package app.com.groupordermaster.service;

import app.com.group.repository.GroupProductImgRepository;
import app.com.group.repository.GroupProductRepository;
import app.com.group.vo.GroupProductImgVO;
import app.com.group.vo.GroupProductVO;
import app.com.grouporderdetail.repository.GroupOrderDetailRepository;
import app.com.grouporderdetail.vo.GroupOrderDetail;
import app.com.groupordermaster.repository.GroupActivityRepository2;
import app.com.groupordermaster.repository.GroupOrderMasterRepository;
import app.com.groupordermaster.repository.GroupOrderMemRepository;
import app.com.groupordermaster.vo.GroupActivity;
import app.com.groupordermaster.vo.GroupOrderMaster;
import app.com.groupordermaster.vo.GroupOrderMemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupOrderMemService {
    @Autowired
    private GroupOrderMemRepository groupOrderMemRepository;
    @Autowired
    private GroupOrderMasterRepository groupOrderMasterRepository;
    @Autowired
    private GroupActivityRepository2 groupActivityRepository2;
    @Autowired
    private GroupProductRepository groupProductRepository;
    @Autowired
    private GroupProductImgRepository groupProductImgRepository;
    @Autowired
    private GroupOrderDetailRepository groupOrderDetailRepository;

    public List<GroupOrderMemDTO> getAllGroupMem(Integer memberID) {
        List<GroupOrderMaster> groupOrderMasters = groupOrderMasterRepository.findAllByMemId(memberID);
        List<GroupOrderMemDTO> dtoList = new ArrayList<>();
        for (GroupOrderMaster g : groupOrderMasters) {
            System.out.println(g);
            GroupOrderMemDTO dto = new GroupOrderMemDTO();
            dto.setActivityId(g.getGroupActivityId());
            dto.setGroupOrderId(g.getGroupOrderId());
            dto.setMemberID(g.getMemId());
            dto.setGroupOrderStatus(g.getGroupOrderStatus());

            GroupActivity groupActivity = groupActivityRepository2.getReferenceById(g.getGroupActivityId());
            dto.setGroupOrderStar(groupActivity.getGroupOrderStar());
            dto.setGroupOrderEnd(groupActivity.getGroupOrderEnd());
            dto.setGroupActivityContent(groupActivity.getGroupActivityContent());
            dto.setGroupName(groupActivity.getGroupName());
            dto.setGroupOrderDiscount(groupActivity.getGroupOrderDiscount());
            dto.setGroupOrderMin(groupActivity.getGroupOrderMin());
            dto.setGroupProductId(groupActivity.getGroupProductId());


            GroupProductVO groupProduct=groupProductRepository.getReferenceById(groupActivity.getGroupProductId());
             dto.setGroupProductId(groupProduct.getGroupProductId());
             dto.setGroupProductName(groupProduct.getGroupProductName());
             dto.setGroupProductPrice(groupProduct.getGroupProductPrice());


            GroupProductImgVO groupProductImg=groupProductImgRepository.getReferenceById(groupActivity.getGroupProductId());

            dtoList.add(dto);
        }
        return dtoList;
    }
}
