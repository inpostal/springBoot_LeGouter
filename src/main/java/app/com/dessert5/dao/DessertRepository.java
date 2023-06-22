package app.com.dessert5.dao;

import app.com.dessert5.vo.Dessert;
import app.com.dessert5.vo.DessertImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DessertRepository extends CrudRepository<Dessert, Integer> {

    Dessert findByDessertName(String dessertName);

    // 根據dessertId找到對應的List<DessertImage>
     List<DessertImage> findByDessertId(Integer dessertId);

}
