package app.com.course.repository;

import app.com.course.vo.CourseImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseImageRepository extends JpaRepository<CourseImage, Integer> {
}
