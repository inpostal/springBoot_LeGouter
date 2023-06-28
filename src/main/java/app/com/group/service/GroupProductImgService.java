package app.com.group.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.com.group.repository.GroupProductImgRepository;
import app.com.group.vo.GroupProductImgVO;

@Service
public class GroupProductImgService {
	
	@Autowired
	private GroupProductImgRepository groupProductImgRepository;
	
	public Boolean inserImg(GroupProductImgVO groupProductImgVO) {
		return groupProductImgRepository.save(groupProductImgVO) != null;
	}
	
	public GroupProductImgVO getImg(Integer groupProductImgId) {
		GroupProductImgVO imgvo = groupProductImgRepository.getReferenceById(groupProductImgId);
		return imgvo;
	}
	
	public Boolean updataImg(GroupProductImgVO groupProductImgVO) {
		return groupProductImgRepository.save(groupProductImgVO) != null;
	}

	public GroupProductImgVO getOneImg(Integer groupProductImgId) {
		Optional<GroupProductImgVO> imgonevo = groupProductImgRepository.findById(groupProductImgId);
		return imgonevo.get();
	}
}
