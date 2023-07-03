package app.com.dessertCart.repository;

import app.com.dessertCart.entity.DessertCart;
import app.com.dessertCart.entity.DessertCartId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * ClassName: DessertCartReposity
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/27 PM 05:04
 */
public interface DessertCartRepository extends JpaRepository<DessertCart, DessertCartId> {
    List<DessertCart> findByMemberId(Integer memberId);

    Optional<DessertCart> findByDessertIdAndMemberId(Integer dessertId, Integer memberId);

    void deleteByDessertIdAndMemberId(Integer dessertId, Integer memberId);
}






