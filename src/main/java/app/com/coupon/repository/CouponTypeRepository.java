package app.com.coupon.repository;

import app.com.coupon.vo.CouponType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponTypeRepository extends JpaRepository<CouponType, Integer> {
    List<CouponType> findAllByCpTp(Integer CpTp);
}
