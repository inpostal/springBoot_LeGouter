package app.com.course.repository;

import app.com.course.vo.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChefRepository  extends JpaRepository<Chef, Integer> {
}
