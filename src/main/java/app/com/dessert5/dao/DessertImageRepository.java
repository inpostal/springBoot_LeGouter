package app.com.dessert5.dao;

import app.com.dessert5.vo.DessertImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DessertImageRepository extends JpaRepository<DessertImage, Integer> {


}
