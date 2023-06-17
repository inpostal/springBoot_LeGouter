package app.com.dessert5.dao;

import app.com.dessert5.vo.Dessert;
import org.springframework.data.repository.CrudRepository;

public interface DessertRepository extends CrudRepository<Dessert, Integer> {

    Dessert findByDessertName(String dessertName);

}
