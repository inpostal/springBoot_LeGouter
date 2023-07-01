package app.com.group.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.com.group.vo.GroupProductImgVO;

public interface GroupProductImgRepository extends JpaRepository<GroupProductImgVO, Integer> {
	
	//20230701 配合多張圖片顯示，以FK為查詢參數。
	public List<GroupProductImgVO> findByGroupProductId(Integer groupProductId);

}
