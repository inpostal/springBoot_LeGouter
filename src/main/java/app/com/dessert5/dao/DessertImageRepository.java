package app.com.dessert5.dao;

import app.com.dessert5.vo.DessertImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DessertImageRepository extends CrudRepository<DessertImage, Integer> {

}
