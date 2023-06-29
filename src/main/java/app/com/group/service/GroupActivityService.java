package app.com.group.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.com.group.repository.GroupActivityRepository;
import app.com.group.vo.GroupActivityDTO;
import app.com.group.vo.GroupActivityVO;
import app.com.group.vo.InserDetailDTO;
import app.com.grouporderdetail.repository.GroupOrderDetailRepository;
import app.com.grouporderdetail.vo.GroupOrderDetail;
import app.com.groupordermaster.repository.GroupOrderMasterRepository;
import app.com.groupordermaster.vo.GroupOrderMaster;

@Service
public class GroupActivityService {

	@Autowired
	private GroupActivityRepository groupActivityRepository;

	@Autowired
	private GroupOrderMasterRepository groupOrderMasterRepository;

	// 用彥君的Repository
	@Autowired
	private GroupOrderDetailRepository groupOrderDetailRepository;

	public List<GroupActivityVO> allActivity() {
		List<GroupActivityVO> avolist = groupActivityRepository.findAll();
		return avolist;
	}

	public List<GroupActivityVO> allShopActivity(Date groupOrderEnd) {
		List<GroupActivityVO> avoaclist = groupActivityRepository.findByGroupOrderEndAfter(groupOrderEnd);
		return avoaclist;
	}

	public GroupActivityVO showTheActivity(Integer groupActivityId) {
//		GroupActivityVO thevo = groupActivityRepository.getReferenceById(groupActivityId);
//		GroupActivityDTO thedto = new GroupActivityDTO();
//		thedto.setGroupActivityId(thevo.getGroupActivityId());
//		thedto.setGroupProductId(thevo.getGroupProductId());
//		thedto.setGroupActivityContent(thevo.getGroupActivityContent());
//		thedto.setGroupOrderStar(thevo.getGroupOrderStar());
//		thedto.setGroupOrderEnd(thevo.getGroupOrderEnd());
//		thedto.setGroupOrderMin(thevo.getGroupOrderMin());
//		thedto.setGroupName(thevo.getGroupName());
//		thedto.setGroupOrderDiscount(thevo.getGroupOrderDiscount());
		Optional<GroupActivityVO> thevo = groupActivityRepository.findById(groupActivityId);
		return thevo.get();
	}

	public GroupActivityVO inserActivitys(GroupActivityDTO groupActivityDTO) {
		GroupActivityVO groupActivityVO = new GroupActivityVO();
		groupActivityVO.setGroupProductId(groupActivityDTO.getGroupProductId());
		groupActivityVO.setGroupActivityContent(groupActivityDTO.getGroupActivityContent());
		groupActivityVO.setGroupOrderStar(groupActivityDTO.getGroupOrderStar());
		groupActivityVO.setGroupOrderEnd(groupActivityDTO.getGroupOrderEnd());
		groupActivityVO.setGroupOrderMin(groupActivityDTO.getGroupOrderMin());
		groupActivityVO.setGroupName(groupActivityDTO.getGroupName());
		groupActivityVO.setGroupOrderDiscount(groupActivityDTO.getGroupOrderDiscount());
		return groupActivityRepository.save(groupActivityVO);
	}

	public Boolean deleteOneActivity(Integer groupActivityId) {
		groupActivityRepository.deleteById(groupActivityId);
		return true;
	}

	// 整合後應該放在彥君的團購訂單主檔Service。
	public GroupOrderMaster inserMaster(GroupOrderMaster groupOrderMaster) {
		return groupOrderMasterRepository.save(groupOrderMaster);
	}

	// 整合後應該放在彥君的團購訂單明細Service。
	public GroupOrderDetail inDetail(InserDetailDTO inserDetailDTO) {
		GroupOrderDetail groupOrderDetail = new GroupOrderDetail();
		groupOrderDetail.setGroupOrderId(inserDetailDTO.getGroupOrderId());
		groupOrderDetail.setMemberId(inserDetailDTO.getMemberId());
//		待補上信箱
		groupOrderDetail.setGroupOrderAmount(inserDetailDTO.getGroupOrderAmount());
		groupOrderDetail.setGroupProductPaying(inserDetailDTO.getGroupProductPaying());
		groupOrderDetail.setGroupProductStatus(inserDetailDTO.getGroupProductStatus());
		groupOrderDetail.setGroupProductOthers(inserDetailDTO.getGroupProductOthers());
		groupOrderDetail.setReceiverName(inserDetailDTO.getReceiverName());
		groupOrderDetail.setReceiverAddress(inserDetailDTO.getReceiverAddress());
		groupOrderDetail.setReceiverEmail(inserDetailDTO.getReceiverEmail());
		groupOrderDetail.setReceiverPhone(inserDetailDTO.getReceiverPhone());
		groupOrderDetail.setGroupProductPrice(inserDetailDTO.getGroupActivityPrice());
		return groupOrderDetailRepository.save(groupOrderDetail);
	}

	//
//	public Boolean updataActivity(GroupActivityDTO groupActivityDTO) {
//		GroupActivityVO groupActivityVO = new GroupActivityVO();
//		groupActivityVO.setGroupActivityId(groupActivityDTO.getGroupActivityId());
//		groupActivityVO.setGroupActivityContent(groupActivityDTO.getGroupActivityContent());
//		groupActivityVO.setGroupName(groupActivityDTO.getGroupName());
//		return groupActivityRepository.save(groupActivityVO) != null;
//	}

}
