package app.com.dessertOrders.repository;


import app.com.dessertOrders.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 持久層
 *
 * @author Charlie
 * @since 2023-06-21 10:35:15
 */
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

}

