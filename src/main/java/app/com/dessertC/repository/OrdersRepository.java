package app.com.dessertC.repository;


import app.com.dessertC.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 持久层
 *
 * @author makejava
 * @since 2023-06-21 10:35:15
 */
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    
}

