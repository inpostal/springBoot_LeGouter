package app.com.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.com.group.vo.GroupProductVO;
import java.util.List;


public interface GroupProductRepository extends JpaRepository<GroupProductVO, Integer> {

	public List<GroupProductVO> findByGroupProductStatus(Integer groupProductStatus);
}
