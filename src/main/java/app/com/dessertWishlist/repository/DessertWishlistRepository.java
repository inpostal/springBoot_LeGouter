package app.com.dessertWishlist.repository;

import app.com.dessertWishlist.vo.DessertWishlist;
import app.com.dessertWishlist.vo.DessertWishlistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DessertWishlistRepository extends JpaRepository<DessertWishlist, DessertWishlistId> {
    Optional<DessertWishlist> findByMemId(Integer memId);

    @Transactional
    void deleteByDessertIdAndMemId(Integer dessertId, Integer memId);

    List<DessertWishlist> findAllByMemId(Integer memId);

}
