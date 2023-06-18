package app.com.group.service;

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

}
