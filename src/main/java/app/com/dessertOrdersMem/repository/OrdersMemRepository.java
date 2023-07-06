package app.com.dessertOrdersMem.repository;


import app.com.dessertOrdersMem.entity.OrdersMem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 持久層
 *
 * @author Charlie
 * @since 2023-06-21 10:35:15
 */
public interface OrdersMemRepository extends JpaRepository<OrdersMem, Integer> {
    List<OrdersMem> findByMemId(Integer memId);

    List<OrdersMem> findByMemIdOrderByOrderTimeDesc(Integer memId);

}


