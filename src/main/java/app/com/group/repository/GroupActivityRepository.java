package app.com.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.com.group.vo.GroupActivityVO;
import java.util.List;
import java.util.Date;


public interface GroupActivityRepository extends JpaRepository<GroupActivityVO, Integer> {
	
	//自定義 查詢結束日期大於當下日期時間的活動資料。
	public List<GroupActivityVO> findByGroupOrderEndAfter(Date groupOrderEnd);

}
