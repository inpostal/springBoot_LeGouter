package app.com.CourseStudentComment.repository;

import app.com.CourseStudentComment.vo.CourseStudentComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseStudentCommentRepository extends JpaRepository<CourseStudentComment, Integer> {
    List<CourseStudentComment> findAllByCourseId(Integer courseId);
}
