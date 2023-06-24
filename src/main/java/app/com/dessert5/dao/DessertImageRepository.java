package app.com.dessert5.dao;

import app.com.dessert5.vo.DessertImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DessertImageRepository extends JpaRepository<DessertImage, Integer> {

    @Modifying
    @Transactional
    @Query(value = "delete from dessert_img where dessert_id = ?1 and dessert_image_map_key = ?2", nativeQuery = true)
    int deleteByDessertIdAndDessertImageMapKey(Integer dessertId, Integer dessertImageMapKey);


}
