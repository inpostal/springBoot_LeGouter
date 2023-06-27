package app.com.group.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.com.group.repository.GroupProductRepository;
import app.com.group.vo.GroupProductDTO;
import app.com.group.vo.GroupProductVO;

@Service
public class GroupProductService {

	@Autowired
	private GroupProductRepository groupProductRepository;
	
	public Boolean inserProduct (GroupProductDTO groupProductDTO) {
		GroupProductVO groupProductVO = new GroupProductVO();
		groupProductVO.setGroupProductName(groupProductDTO.getGroupProductName());
		groupProductVO.setGroupProductContent(groupProductDTO.getGroupProductContent());
		groupProductVO.setGroupProductPrice(groupProductDTO.getGroupProductPrice());
		groupProductVO.setGroupProductStardate(Date.valueOf(groupProductDTO.getGroupProductStardate()));
		groupProductVO.setGroupProductStatus(groupProductDTO.getGroupProductStatus());
		
		return groupProductRepository.save(groupProductVO) != null;
	}
	
	public List<GroupProductVO> showAllProduct() {
		List<GroupProductVO> volist = groupProductRepository.findAll();
		return volist;
	}
	
	public GroupProductVO showOneProduct(Integer groupProductId) {
//		GroupProductVO vo = groupProductRepository.getReferenceById(groupProductId);
//		GroupProductDTO dto = new GroupProductDTO();
//		dto.setGroupProductId(vo.getGroupProductId());
//		dto.setGroupProductName(vo.getGroupProductName());
//		dto.setGroupProductContent(vo.getGroupProductContent());
//		dto.setGroupProductPrice(vo.getGroupProductPrice());
//		dto.setGroupProductStardate(vo.getGroupProductStardate().toString());
//		if (vo.getGroupProductEnddate() != null) {
//			dto.setGroupProductEnddate(vo.getGroupProductEnddate().toString());
//		}
//		dto.setGroupProductStatus(vo.getGroupProductStatus());
		Optional<GroupProductVO> vo = groupProductRepository.findById(groupProductId);
		return vo.get();
	}
	
	public GroupProductVO inserProductNew (GroupProductDTO groupProductDTO) {
		GroupProductVO groupProductVO = new GroupProductVO();
		groupProductVO.setGroupProductName(groupProductDTO.getGroupProductName());
		groupProductVO.setGroupProductContent(groupProductDTO.getGroupProductContent());
		groupProductVO.setGroupProductPrice(groupProductDTO.getGroupProductPrice());
		groupProductVO.setGroupProductStardate(Date.valueOf(groupProductDTO.getGroupProductStardate()));
		groupProductVO.setGroupProductStatus(groupProductDTO.getGroupProductStatus());
		return groupProductRepository.save(groupProductVO);
	}
	
	public Boolean updataThe (GroupProductDTO groupProductDTO) {
		GroupProductVO groupProductVO = new GroupProductVO();
		groupProductVO.setGroupProductId(groupProductDTO.getGroupProductId());
		groupProductVO.setGroupProductName(groupProductDTO.getGroupProductName());
		groupProductVO.setGroupProductContent(groupProductDTO.getGroupProductContent());
		groupProductVO.setGroupProductPrice(groupProductDTO.getGroupProductPrice());
		groupProductVO.setGroupProductStardate(Date.valueOf(groupProductDTO.getGroupProductStardate()));
//		System.out.println("GroupProductEnddate的值為:" + groupProductDTO.getGroupProductEnddate());
		if (groupProductDTO.getGroupProductEnddate().trim().length() != 0) {
			groupProductVO.setGroupProductEnddate(Date.valueOf(groupProductDTO.getGroupProductEnddate()));
		}
		groupProductVO.setGroupProductStatus(groupProductDTO.getGroupProductStatus());
		return groupProductRepository.save(groupProductVO) != null;
	}
}
