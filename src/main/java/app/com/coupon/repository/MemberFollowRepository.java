package app.com.coupon.repository;

import app.com.coupon.vo.LoveCourse;
import app.com.coupon.vo.LoveCourseMemDTO;
import app.com.coupon.vo.MembersCp;
import app.com.coupon.vo.MembersCpId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberFollowRepository extends JpaRepository <LoveCourse, LoveCourse>{

    List<LoveCourse> findAllByMemId(Integer memberId);
}
