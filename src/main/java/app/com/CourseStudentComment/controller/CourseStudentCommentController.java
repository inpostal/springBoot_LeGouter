package app.com.CourseStudentComment.controller;

import app.com.CourseStudentComment.service.CourseStudentCommentService;
import app.com.CourseStudentComment.vo.CourseCommentDTO;

import app.com.CourseStudentComment.vo.CourseStudentComment;
import app.com.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CourseStudentCommentController {
    @Autowired
    private CourseStudentCommentService courseStudentCommentService;
    @Autowired
    CourseService courseService;
@GetMapping("/CourseStudentComment")
    public String getAll(Model model){
        List<CourseCommentDTO>result = courseStudentCommentService.courseComment();
model.addAttribute("courseComment",result);
        return "/back-end/CourseStudentComment/CourseStudentComment";
    }

    //個別回復
    @GetMapping("/CourseStudentComment/reply")
    public String reply(@RequestParam Integer commentId, Model model) {
        CourseCommentDTO courseCommentDTO = courseStudentCommentService.getCourseCommentById(commentId);
        model.addAttribute("courseComment", courseCommentDTO);
        return "/back-end/CourseStudentComment/replyComment";
    }

    @GetMapping("/CourseStudentComment/comment")
    public String comment() {
        return "/front-end/CourseStudentComment/coursecomment";
    }
@PostMapping("/CourseStudentComment/addcomment")
    public ResponseEntity<?>addcomment(
            @RequestParam Integer memId,
            @RequestParam Integer courseId,
            @RequestParam String commentContent) {
        CourseStudentComment courseStudentComment= new CourseStudentComment();
        courseStudentComment.setMemId(memId);
        courseStudentComment.setCourseId(courseId);
        courseStudentComment.setStudentCommentContent(commentContent);
        courseStudentCommentService.add(courseStudentComment);
        return ResponseEntity.ok().body("新增成功");


    }
}

