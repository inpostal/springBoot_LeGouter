package app.com.CourseStudentComment.repository;

import app.com.CourseStudentComment.vo.CourseStudentComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseStudentCommentRepository extends JpaRepository<CourseStudentComment, Integer> {
}
