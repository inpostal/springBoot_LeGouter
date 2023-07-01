package app.com.groupordermaster.repository;

import app.com.groupordermaster.vo.GroupOrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupOrderMemRepository extends JpaRepository<GroupOrderMaster, Integer> {
 List<GroupOrderMaster> findAllByMemId(Integer memberID);

}
