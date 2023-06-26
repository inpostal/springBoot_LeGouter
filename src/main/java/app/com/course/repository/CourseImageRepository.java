package app.com.course.repository;

import app.com.course.vo.CourseImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseImageRepository extends JpaRepository<CourseImage, Integer> {
    List<CourseImage> findAllByCourseId(Integer courseId);

}
