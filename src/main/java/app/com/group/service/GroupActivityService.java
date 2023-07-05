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
import app.com.grouporderdetail.vo.GroupOrderDetailId;
import app.com.groupordermaster.repository.GroupOrderMasterRepository;
import app.com.groupordermaster.vo.GroupOrderMaster;
import app.com.member.repository.MemberRepository;
import app.com.member.vo.Members;

@Service
public class GroupActivityService {

	@Autowired
	private GroupActivityRepository groupActivityRepository;

	// 用彥君的Repository
	@Autowired
	private GroupOrderMasterRepository groupOrderMasterRepository;

	// 用彥君的Repository
	@Autowired
	private GroupOrderDetailRepository groupOrderDetailRepository;
	
	// 用奕翔的Repository
	@Autowired
	private MemberRepository memberRepository;

	public List<GroupActivityVO> allActivity() {
		List<GroupActivityVO> avolist = groupActivityRepository.findAll();
		return avolist;
	}

	public List<GroupActivityVO> allShopActivity(Date groupOrderEnd) { //參數實際為當下日期時間。
//		List<GroupActivityVO> avoaclist = groupActivityRepository.findByGroupOrderEndAfter(groupOrderEnd);
		List<GroupActivityVO> avoaclist = groupActivityRepository.findByGroupOrderStarLessThanEqualAndGroupOrderEndAfter(groupOrderEnd, groupOrderEnd);
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
//		System.out.println("第二層觀察抓到的數量:" + inserDetailDTO.getGroupOrderAmount());
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
	
	// 整合後應該放在彥君的團購訂單明細Service。
	public Long getDetailCount(Integer groupActivityId) {
		//自定義以活動編號為查詢條件。
		List<GroupOrderMaster> groupOrderMaster = groupOrderMasterRepository.findByGroupActivityId(groupActivityId);
		//自定義以活動編號為計數。
		System.out.println("檢查findByGroupActivityId回傳的陣列長度:" + groupOrderMaster.size());
		byte setgroupProductStatus = 1;
		return groupOrderDetailRepository.countByGroupOrderIdAndGroupProductStatus(groupOrderMaster.get(0).getGroupOrderId(), setgroupProductStatus);
	}
	
	// 整合後應該放在彥君的團購訂單明細Service。
		public GroupOrderMaster inMasterSum(Integer groupOrderId) {
			List<GroupOrderDetail> total = groupOrderDetailRepository.findByGroupOrderId(groupOrderId);
			int numberproduct = 0;
			int totalproductprice = 0;
			for (GroupOrderDetail onebyone : total) {
				if (onebyone.getGroupProductStatus() == 1) {
					numberproduct += onebyone.getGroupOrderAmount();
					totalproductprice += (onebyone.getGroupOrderAmount() * onebyone.getGroupProductPrice());
				}
			}
			GroupOrderMaster groupOrderMaster = groupOrderMasterRepository.findById(groupOrderId).get();
			Integer thegroupOrderMin = groupActivityRepository.findById(groupOrderMaster.getGroupActivityId()).get().getGroupOrderMin();
			double percentage = 0.04;
			groupOrderMaster.setGroupOrderId(groupOrderId);
			groupOrderMaster.setNumberOfProduct(numberproduct);
//			groupOrderMaster.setGroupOrderBonus((int) Math.round(totalproductprice * thegroupOrderDiscount.doubleValue()));
			switch (thegroupOrderMin) {
			case 200:
				percentage = 0.04;
				break;
			case 400:
				percentage = 0.08;
				break;
			case 600:
				percentage = 0.12;
				break;
			}
			groupOrderMaster.setGroupOrderBonus((int) Math.round(totalproductprice * percentage));
			groupOrderMaster.setTotalGroupProductPrice(totalproductprice);
			
			return groupOrderMasterRepository.save(groupOrderMaster);
		}
		
		public Members CheckGetMember(Integer memberId) {
			Members mem = memberRepository.findById(memberId).get();
			return mem;
		}
		
		public List<GroupActivityVO> ActivityKeyName(String keywords, Date groupOrderStar, Date groupOrderEnd) {
//			return groupActivityRepository.findByGroupNameContaining(keywords);
			return groupActivityRepository.findByGroupNameContainingAndGroupOrderStarLessThanEqualAndGroupOrderEndAfter(keywords, groupOrderStar, groupOrderEnd);
		}
		
		public List<GroupActivityVO> BackKeyContent(String groupActivityContent) {
			return groupActivityRepository.findByGroupActivityContentContaining(groupActivityContent);
		}
		
		public List<GroupActivityVO> BackKeyName(String groupName) {
			return groupActivityRepository.findByGroupNameContaining(groupName);
		}
		
		public Boolean ConfirmDetail(Integer groupActivityId, Integer memberId) {
			GroupOrderDetailId idpks = new GroupOrderDetailId();
			idpks.setGroupOrderId(groupActivityId);
			idpks.setMemberId(memberId);
			Optional<GroupOrderDetail> outConfirm = groupOrderDetailRepository.findById(idpks);
			if (! outConfirm.isPresent()) { //採到資料不存在的雷。
				return false;
			}else {
				return outConfirm.get() != null;
			}
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
