package app.com.group.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import app.com.group.repository.GroupProductImgRepository;
import app.com.group.vo.GroupProductImgVO;

@Service
public class GroupProductImgService {

	@Autowired
	private GroupProductImgRepository groupProductImgRepository;

	public Boolean inserImg(GroupProductImgVO groupProductImgVO) {
		return groupProductImgRepository.save(groupProductImgVO) != null;
	}

	public GroupProductImgVO getImg(Integer groupProductId) {
		List<GroupProductImgVO> imgvo = groupProductImgRepository.findByGroupProductId(groupProductId);
		return imgvo.get(0);
	}

	public Boolean updataImg(GroupProductImgVO groupProductImgVO) {
		return groupProductImgRepository.save(groupProductImgVO) != null;
	}

	public GroupProductImgVO getPkImg(Integer groupProductImgId) {
		Optional<GroupProductImgVO> imgonevo = groupProductImgRepository.findById(groupProductImgId);
		return imgonevo.get();
	}

	public Boolean inser4Img(Integer groupProductId, MultipartFile file0, MultipartFile file1, MultipartFile file2,
			MultipartFile file3) {
		MultipartFile[] files = { file0, file1, file2, file3 };
		for (int i = 0; i < files.length; i++) {
			GroupProductImgVO inserivo = new GroupProductImgVO();
			System.out.println("每次回圈觀察MultipartFile陣列長度:" + files.length);
			if (files[i] != null) {
				try {
					// 取得該商品VO物件的AutoID，帶入成為FK之值。
					inserivo.setGroupProductId(groupProductId);
					inserivo.setGroupProductImg(files[i].getBytes());
					groupProductImgRepository.save(inserivo);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	public List<GroupProductImgVO> getFkImg(Integer groupProductId) {
		List<GroupProductImgVO> imgonevo = groupProductImgRepository.findByGroupProductId(groupProductId);
		return imgonevo;
	}
}
