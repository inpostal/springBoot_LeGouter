package app.com.groupordermaster.service;

import app.com.groupordermaster.repository.GroupActivityRepository2;
import app.com.groupordermaster.repository.GroupOrderMasterRepository;
import app.com.groupordermaster.vo.GroupActivity;
import app.com.groupordermaster.vo.GroupOrderBonusDTO;
import app.com.groupordermaster.vo.GroupOrderMaster;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service

public class GroupOrderBonusService {
    @Autowired
    private GroupOrderMasterRepository repository1;
    @Autowired
    private GroupActivityRepository2 repository2;

    public List<GroupOrderBonusDTO> getAllGroupOrderMasters() {
        List<GroupOrderMaster> groupOrderMaster = repository1.findAll();

        List<GroupOrderBonusDTO> result = new ArrayList<>();

        for (GroupOrderMaster g :
                groupOrderMaster) {
            GroupOrderBonusDTO dto = new GroupOrderBonusDTO();
            GroupActivity groupActivity = repository2.getReferenceById(g.getGroupActivityId());

            dto.setGroupOrderId(g.getGroupOrderId());
            dto.setActivityId(g.getGroupActivityId());
            dto.setMemberID(g.getMemId());
            dto.setGroupOrderStar(groupActivity.getGroupOrderStar());
            dto.setGroupOrderEnd(groupActivity.getGroupOrderEnd());
            dto.setTotalGroupProductPrice(g.getTotalGroupProductPrice());
            dto.setGroupOrderBonus(g.getGroupOrderBonus());
            dto.setGroupOrderStatus(g.getGroupOrderStatus());


            switch (groupActivity.getGroupOrderMin()) {
                case 200:
                    dto.setBonusRate("8%");
                    break;
                case 400:
                    dto.setBonusRate("10%");
                    break;
                case 600:
                    dto.setBonusRate("12%");
                    break;
            }


            if (g.getGroupOrderBonusStatus() == 0) {
                dto.setGroupOrderBonusStatus("未發放");
            } else {
                dto.setGroupOrderBonusStatus("已發放");
            }



            result.add(dto);
        }
        return result;
    }


    //前台團購主查詢,用memId找到
    public List<GroupOrderBonusDTO> frontlist(Integer memberId) {
        List<GroupOrderMaster> list = repository1.findAllByMemId(memberId);
        List<GroupOrderBonusDTO> result = new ArrayList<>();

        for (GroupOrderMaster g :
                list) {
            GroupOrderBonusDTO dto = new GroupOrderBonusDTO();
            GroupActivity groupActivity = repository2.getReferenceById(g.getGroupActivityId());

            dto.setGroupOrderId(g.getGroupOrderId());
            dto.setActivityId(g.getGroupActivityId());
            dto.setMemberID(g.getMemId());
            dto.setGroupOrderStar(groupActivity.getGroupOrderStar());
            dto.setGroupOrderEnd(groupActivity.getGroupOrderEnd());
            dto.setTotalGroupProductPrice(g.getTotalGroupProductPrice());
            dto.setGroupOrderBonus(g.getGroupOrderBonus());
            dto.setGroupOrderStatus(g.getGroupOrderStatus());



            switch (groupActivity.getGroupOrderMin()) {
                case 200:
                    dto.setBonusRate("8%");
                    break;
                case 400:
                    dto.setBonusRate("10%");
                    break;
                case 600:
                    dto.setBonusRate("12%");
                    break;
            }





            result.add(dto);
        }
        return result;
    }


    public void updatebonus(Integer groupOrderId, Integer groupOrderBonusStatus) {
        GroupOrderMaster groupOrderMaster = repository1.getReferenceById(groupOrderId);
        groupOrderMaster.setGroupOrderBonusStatus(groupOrderBonusStatus);
        repository1.save(groupOrderMaster);
    }


}







