package app.com.CourseStudentComment.repository;

import app.com.CourseStudentComment.vo.CourseStudentComment;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CourseStudentCommentRepository extends PagingAndSortingRepository<CourseStudentComment, Integer> {
    List<CourseStudentComment> findAllByCourseId(Integer courseId);
}
