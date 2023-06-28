package app.com.dessertCart.repository;

import app.com.dessertCart.entity.DessertCart;
import app.com.member.vo.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * ClassName: DessertCartReposity
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/27 PM 05:04
 */
@Repository
public interface DessertCartRepository extends JpaRepository<DessertCart, Integer> {
    List<DessertCart> findByMember(Members member);

    Optional<DessertCart> findById(int dessertCartId);
}



