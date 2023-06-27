package app.com.dessertCart.repository;

import app.com.dessertCart.entity.DessertCart;
import app.com.dessertCart.entity.DessertCartId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClassName: DessertCartReposity
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/27 PM 05:04
 */
public interface DessertCartRepository extends JpaRepository<DessertCart, DessertCartId> {
}
