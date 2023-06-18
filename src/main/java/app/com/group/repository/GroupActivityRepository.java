package app.com.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.com.group.vo.GroupActivityVO;

public interface GroupActivityRepository extends JpaRepository<GroupActivityVO, Integer> {

}
