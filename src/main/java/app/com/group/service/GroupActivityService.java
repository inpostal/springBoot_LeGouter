package app.com.group.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.com.group.repository.GroupActivityRepository;
import app.com.group.vo.GroupActivityDTO;
import app.com.group.vo.GroupActivityVO;

@Service
public class GroupActivityService {

	@Autowired
	private GroupActivityRepository groupActivityRepository;
	
	public List<GroupActivityVO> allActivity() {
		List<GroupActivityVO> avolist = groupActivityRepository.findAll();
		return avolist;
	}
	
	public List<GroupActivityVO> allShopActivity() {
		List<GroupActivityVO> avoaclist = groupActivityRepository.findAll();
		return avoaclist;
	}
	
	public GroupActivityDTO showTheActivity(Integer groupActivityId) {
		GroupActivityVO thevo = groupActivityRepository.getReferenceById(groupActivityId);
		GroupActivityDTO thedto = new GroupActivityDTO();
		thedto.setGroupActivityId(thevo.getGroupActivityId());
		thedto.setGroupProductId(thevo.getGroupProductId());
		thedto.setGroupActivityContent(thevo.getGroupActivityContent());
		thedto.setGroupOrderStar(thevo.getGroupOrderStar());
		thedto.setGroupOrderEnd(thevo.getGroupOrderEnd());
		thedto.setGroupOrderMin(thevo.getGroupOrderMin());
		thedto.setGroupName(thevo.getGroupName());
		thedto.setGroupOrderDiscount(thevo.getGroupOrderDiscount());
				return thedto;
	}
	
	public Boolean inserActivitys (GroupActivityDTO groupActivityDTO) {
		GroupActivityVO groupActivityVO = new GroupActivityVO();
		groupActivityVO.setGroupProductId(groupActivityDTO.getGroupProductId());
		groupActivityVO.setGroupActivityContent(groupActivityDTO.getGroupActivityContent());
		groupActivityVO.setGroupOrderStar(groupActivityDTO.getGroupOrderStar());
		groupActivityVO.setGroupOrderEnd(groupActivityDTO.getGroupOrderEnd());
		groupActivityVO.setGroupOrderMin(groupActivityDTO.getGroupOrderMin());
		groupActivityVO.setGroupName(groupActivityDTO.getGroupName());
		groupActivityVO.setGroupOrderDiscount(groupActivityDTO.getGroupOrderDiscount());
		return groupActivityRepository.save(groupActivityVO) != null;
	}
	
	public Boolean deleteOneActivity (Integer groupActivityId) {
		groupActivityRepository.deleteById(groupActivityId);
		return true;
	}
}
