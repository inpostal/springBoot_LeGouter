package app.com.course.repository;

import app.com.course.vo.CourseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseOrderRepository2 extends JpaRepository<CourseOrder, Integer> {
}
