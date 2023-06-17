package app.com.group.service;

import java.sql.Date;

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
}
