package app.com.CourseStudentComment.service;

import app.com.CourseStudentComment.repository.CourseStudentCommentRepository;
import app.com.CourseStudentComment.vo.CourseCommentDTO;
import app.com.CourseStudentComment.vo.CourseStudentComment;
import app.com.course.repository.CourseRepository;

import app.com.course.vo.Course;
import app.com.emp.repository.EmployeeRepository;
import app.com.emp.vo.Employee;
import app.com.member.repository.MemberRepository;
import app.com.member.vo.Members;
import app.com.news.vo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class CourseStudentCommentService {
    @Autowired
    private CourseStudentCommentRepository repository1;
    @Autowired
    CourseRepository repository;
    @Autowired
    EmployeeRepository employeeRepository;

    //列表
    public Page<CourseCommentDTO> courseComment(Pageable pageable) {
        Page<CourseStudentComment> page = repository1.findAll(pageable);
        List<CourseStudentComment> courseStudentComment = page.getContent();
        List<CourseCommentDTO> result = new ArrayList<>();

        for (CourseStudentComment c : courseStudentComment) {
            CourseCommentDTO dto = new CourseCommentDTO();
            Course course = repository.getReferenceById(c.getCourseId());
            if (c.getEmpId() != null) {
                Employee employee = employeeRepository.getReferenceById(c.getEmpId());
                dto.setEmpName(employee.getEmpName());
            }

            dto.setMemId(c.getMemId());
            dto.setCourseName(course.getCourseName());
            dto.setCourseId(c.getCourseId());
            dto.setCourseStudentCommentId(c.getStudentCommentId());
            dto.setCourseStudentCommentContent(c.getStudentCommentContent());
            dto.setCourseStudentCommentDate(c.getStudentCommentDate());
            dto.setChefCommentContent(c.getChefCommentContent());
            dto.setChefCommentDate(c.getChefCommentDate());
            result.add(dto);

            // 如果没有厨师回复，显示为未回复；如果有值，显示为已回复
            if (c.getChefCommentContent() == null) {
                dto.setChefCommentContent("未回复");
            } else {
                dto.setChefCommentContent("已回复");
            }
        }

        return new PageImpl<>(result, pageable, page.getTotalElements());
    }

    //回覆留言
    public CourseCommentDTO getCourseCommentById(Integer courseStudentCommentId) {
        CourseCommentDTO dto = new CourseCommentDTO();
        CourseStudentComment c = repository1.findById(courseStudentCommentId).orElse(null);
        if (c.getEmpId()!=null){
            Employee employee= employeeRepository.getReferenceById(c.getEmpId());
            dto.setEmpId(employee.getEmpId());
            dto.setEmpName(employee.getEmpName());
        }

        if (c != null) {
            dto.setMemId(c.getMemId());
            dto.setCourseId(c.getCourseId());
            dto.setCourseStudentCommentId(c.getStudentCommentId());
            dto.setCourseStudentCommentContent(c.getStudentCommentContent());
            dto.setCourseStudentCommentDate(c.getStudentCommentDate());

            dto.setChefCommentContent(c.getChefCommentContent());
            dto.setChefCommentDate(c.getChefCommentDate());

        }
        Course course = repository.findById(c.getCourseId()).orElse(null);
        if (course != null) {
            dto.setCourseName(course.getCourseName());
        }else {
            dto.setCourseName("無課程");
        }

//        if (c.getChefCommentContent() == null) {
//            dto.setChefCommentContent("等待回覆中...");
//        } else {
//            dto.setChefCommentContent(c.getChefCommentContent());
//        }
        return dto;
    }

    //新增留言
    public CourseStudentComment add(CourseStudentComment courseStudentComment) {
        return repository1.save(courseStudentComment);
    }

    //修改
    public CourseStudentComment getById(Integer courseStudentCommentId) {
        Optional<CourseStudentComment> update = repository1.findById(courseStudentCommentId);
        return update.get();
    }

    public void update(CourseStudentComment courseStudentComment) {
        //如果有值就顯示已回覆
        courseStudentComment.setChefCommentDate(Date.valueOf(java.time.LocalDate.now()));
        repository1.save(courseStudentComment);
    }
//刪除
    public void delete(Integer courseStudentCommentId) {
        repository1.deleteById(courseStudentCommentId);
    }

    public List<CourseCommentDTO> findAllCommentByCourseId(Integer courseId) {
        List<CourseStudentComment> courseStudentComment = repository1.findAllByCourseId(courseId);
        List<CourseCommentDTO> result = new ArrayList<>();


        for (CourseStudentComment c : courseStudentComment) {
            CourseCommentDTO dto = new CourseCommentDTO();
            Course course = repository.getReferenceById(c.getCourseId());
            if (c.getEmpId() != null) {
                Employee employee = employeeRepository.getReferenceById(c.getEmpId());
                dto.setEmpName(employee.getEmpName());
            }
            dto.setMemId(c.getMemId());
            dto.setCourseName(course.getCourseName());
            dto.setCourseId(c.getCourseId());
            dto.setCourseStudentCommentId(c.getStudentCommentId());
            dto.setCourseStudentCommentContent(c.getStudentCommentContent());
            dto.setCourseStudentCommentDate(c.getStudentCommentDate());
            dto.setChefCommentContent(c.getChefCommentContent());
            dto.setChefCommentDate(c.getChefCommentDate());
            result.add(dto);
        }
        return result;
    }


//    public List<CourseStudentComment> getAllComment(){
//    List<CourseStudentComment>getAllComment=repository1.findAll();
//    return getAllComment;
//    }
//}
}
