package app.com.CourseOrder.repository;

import app.com.CourseOrder.vo.CourseOrderDetail;
import app.com.coupon.vo.CouponType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseOrderDerailRepository extends JpaRepository<CourseOrderDetail, Integer> {
}
