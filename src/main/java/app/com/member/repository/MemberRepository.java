package app.com.member.repository;

import app.com.member.vo.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members, Integer> {

}
