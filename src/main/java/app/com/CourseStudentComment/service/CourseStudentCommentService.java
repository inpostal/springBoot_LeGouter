package app.com.CourseStudentComment.service;

import app.com.CourseStudentComment.repository.CourseStudentCommentRepository;
import app.com.CourseStudentComment.vo.CourseCommentDTO;
import app.com.CourseStudentComment.vo.CourseStudentComment;
import app.com.course.repository.CourseRepository;
import app.com.course.vo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseStudentCommentService {
    @Autowired
    private CourseStudentCommentRepository repository1;
    @Autowired
    CourseRepository repository;

    //列表
    public List<CourseCommentDTO> courseComment() {
        List<CourseStudentComment> courseStudentComment = repository1.findAll();
        List<CourseCommentDTO> result = new ArrayList<>();

        for (CourseStudentComment c : courseStudentComment) {
            CourseCommentDTO dto = new CourseCommentDTO();
            Course course = repository.getReferenceById(c.getCourseId());
            dto.setMemId(c.getMemId());
            dto.setCourseName(course.getCourseName());
            dto.setCourseId(c.getCourseId());
            dto.setCourseStudentCommentId(c.getStudentCommentId());
            dto.setCourseStudentCommentContent(c.getStudentCommentContent());
            dto.setCourseStudentCommentDate(c.getStudentCommentDate());
            dto.setEmpId(c.getEmpId());
            dto.setChefCommentContent(c.getChefCommentContent());
            dto.setChefCommentDate(c.getChefCommentDate());
            result.add(dto);
        }

        return result;
    }

    //回覆留言
    public CourseCommentDTO getCourseCommentById(Integer courseStudentCommentId) {
        CourseCommentDTO dto = new CourseCommentDTO();
        CourseStudentComment c = repository1.findById(courseStudentCommentId).orElse(null);
        if (c != null) {
            dto.setMemId(c.getMemId());
            dto.setCourseId(c.getCourseId());
            dto.setCourseStudentCommentId(c.getStudentCommentId());
            dto.setCourseStudentCommentContent(c.getStudentCommentContent());
            dto.setCourseStudentCommentDate(c.getStudentCommentDate());
            dto.setEmpId(c.getEmpId());
            dto.setChefCommentContent(c.getChefCommentContent());
            dto.setChefCommentDate(c.getChefCommentDate());
        }
        Course course = repository.findById(c.getCourseId()).orElse(null);
        if (course != null) {
            dto.setCourseName(course.getCourseName());
        }
        return dto;
    }

    //新增留言
    public CourseStudentComment add(CourseStudentComment courseStudentComment) {
        return repository1.save(courseStudentComment);
    }
}