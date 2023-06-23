package app.com.dessert5.dao;

import app.com.dessert5.vo.Dessert;
import app.com.dessert5.vo.DessertImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface DessertRepository extends JpaRepository<Dessert, Integer> {

    Dessert findByDessertName(String dessertName);


}
