package app.com.coupon.repository;

import app.com.coupon.vo.MembersCp;
import app.com.coupon.vo.MembersCpId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberCpRepository extends JpaRepository <MembersCp , MembersCpId>{
    List<MembersCp> findAllByMemId(Integer id);
}
