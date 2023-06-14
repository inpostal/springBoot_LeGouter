package app.test.repository;

import app.test.vo.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members, Integer> {

}
