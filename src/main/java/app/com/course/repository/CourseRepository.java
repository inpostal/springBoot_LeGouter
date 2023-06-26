package app.com.course.repository;

import app.com.course.vo.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course getReferenceByCourseName(String courseName);

    Course findByCourseName(String courseName);
}
