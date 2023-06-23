package app.com.coupon.repository;

import app.com.coupon.vo.CouponType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponTypeRepository extends JpaRepository<CouponType, Integer> {
}
