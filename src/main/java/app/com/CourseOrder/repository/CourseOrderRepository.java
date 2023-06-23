package app.com.CourseOrder.repository;

import app.com.CourseOrder.vo.CourseOrder;
import app.com.coupon.vo.CouponType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseOrderRepository extends JpaRepository<CourseOrder, Integer> {
}
