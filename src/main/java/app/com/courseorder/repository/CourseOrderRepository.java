package app.com.courseorder.repository;

import app.com.courseorder.vo.CourseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseOrderRepository extends JpaRepository<CourseOrder, Integer> {
    List<CourseOrder> findAllByMemId(Integer memberId);
    Optional<CourseOrder> findByMemIdAndCourseId(Integer memberId, Integer courseId);
}
