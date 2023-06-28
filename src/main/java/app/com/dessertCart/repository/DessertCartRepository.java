package app.com.dessertCart.repository;

import app.com.dessertCart.entity.DessertCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ClassName: DessertCartReposity
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/27 PM 05:04
 */
public interface DessertCartRepository extends JpaRepository<DessertCart, Integer> {

    List<DessertCart> findByMemberId(Integer memberId);

}






