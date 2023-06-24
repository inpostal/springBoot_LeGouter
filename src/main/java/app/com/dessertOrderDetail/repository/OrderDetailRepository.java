package app.com.dessertOrderDetail.repository;

import app.com.dessertOrderDetail.entity.OrderDetail;
import app.com.dessertOrderDetail.entity.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ClassName: OrderDetailRepository
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/24 AM 12:15
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
    List<OrderDetail> findByOrderId(Integer orderId);

    List<OrderDetail> findAllByOrderId(Integer orderId);
}
