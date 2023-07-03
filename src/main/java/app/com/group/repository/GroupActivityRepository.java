package app.com.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.com.group.vo.GroupActivityVO;
import java.util.List;
import java.util.Date;


public interface GroupActivityRepository extends JpaRepository<GroupActivityVO, Integer> {
	
	//自定義 查詢結束日期大於當下日期時間的活動資料。
	public List<GroupActivityVO> findByGroupOrderEndAfter(Date groupOrderEnd);
	
	//自定義 查詢開始日期小於等於當下日期時間&結束日期大於當下日期時間 的活動資料
	public List<GroupActivityVO> findByGroupOrderStarLessThanEqualAndGroupOrderEndAfter(Date groupOrderStar, Date groupOrderEnd);

	//自定義 前台查詢團購活動名稱關鍵字&開始日期小於等於當下日期時間&結束日期大於當下日期時間 的活動資料
	public List<GroupActivityVO> findByGroupNameContainingAndGroupOrderStarLessThanEqualAndGroupOrderEndAfter(String keywords, Date groupOrderStar, Date groupOrderEnd);
	
	//自定義 後台查詢團購活動名稱關鍵字
	public List<GroupActivityVO> findByGroupNameContaining(String keywords);
	
	//自定義 後台查詢團購活動內容關鍵字
	public List<GroupActivityVO> findByGroupActivityContentContaining(String keywords);
}
