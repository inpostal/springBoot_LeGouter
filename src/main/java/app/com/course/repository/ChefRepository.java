package app.com.course.repository;

import app.com.course.vo.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChefRepository extends JpaRepository<Chef, Integer> {
    Chef getReferenceByEmpId(Integer empId);

    Optional<Chef> findByEmpId(Integer empId);
}
