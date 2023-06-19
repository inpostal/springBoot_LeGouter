package app.com.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.com.group.vo.GroupProductVO;

public interface GroupProductRepository extends JpaRepository<GroupProductVO, Integer> {

}
