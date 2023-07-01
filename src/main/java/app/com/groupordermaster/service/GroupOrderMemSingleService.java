package app.com.groupordermaster.service;


import app.com.group.repository.GroupProductImgRepository;
import app.com.group.repository.GroupProductRepository;
import app.com.group.vo.GroupProductVO;
import app.com.groupordermaster.repository.GroupActivityRepository2;
import app.com.groupordermaster.vo.GroupActivity;
import app.com.groupordermaster.vo.GroupOrderMemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class GroupOrderMemSingleService {
    @Autowired
   private GroupActivityRepository2 groupActivityRepository2;
    @Autowired
    private GroupProductRepository groupProductRepository;
    @Autowired
    private GroupProductImgRepository groupProductImgRepository;



    public GroupOrderMemDTO getGroupOrderMemSingle(Integer groupActivityId){
        GroupActivity groupActivity = groupActivityRepository2.getReferenceById(groupActivityId);

        GroupOrderMemDTO dto=new GroupOrderMemDTO();
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

        return dto;
    }





}
