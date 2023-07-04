package app.com.CourseStudentComment.controller;

import app.com.CourseStudentComment.service.CourseStudentCommentService;
import app.com.CourseStudentComment.vo.CourseCommentDTO;

import app.com.CourseStudentComment.vo.CourseStudentComment;
import app.com.course.service.ChefService;
import app.com.course.service.CourseImageService;
import app.com.course.service.CourseService;
import app.com.course.vo.Chef;
import app.com.course.vo.Course;
import app.com.course.vo.CourseImage;
import app.com.emp.repository.EmployeeRepository;
import app.com.emp.service.EmployeeService;
import app.com.emp.vo.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
public class CourseStudentCommentController {
    @Autowired
    private CourseStudentCommentService courseStudentCommentService;
    @Autowired
    CourseService courseService;
    @Autowired
    ChefService chefService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CourseImageService courseImageService;
    //thyleaf評論列表
    //後台討論區管理列表
@GetMapping("/CourseStudentComment")
    public String getAll(Model model, HttpSession session){
    Employee employee= (Employee) session.getAttribute("emp");
    if (employee==null){
        return "redirect:/employee/login";
    }else {
        if(employee.getEmpClassify()!=2){
            return"redirect:/employee/data";
        } List<CourseCommentDTO>result = courseStudentCommentService.courseComment();
        model.addAttribute("courseComment",result);
        return "/back-end/CourseStudentComment/CourseStudentComment";
    }
    }

    //個別回復表單thyleaf
    @GetMapping("/CourseStudentComment/reply")
    public String reply(@RequestParam Integer commentId, Model model) {
        CourseCommentDTO courseCommentDTO = courseStudentCommentService.getCourseCommentById(commentId);
        model.addAttribute("courseComment", courseCommentDTO);
        return "/back-end/CourseStudentComment/replyComment";
    }

    //主廚修改回覆
    @PostMapping("/CourseStudentComment/updatechefreplay")
    public ResponseEntity<?> updatechefreplay(

            @RequestParam("commentId") Integer courseStudentCommentId,
            @RequestParam("courseId") Integer courseId,
            @RequestParam("chefCommentContent") String chefCommentContent,
            @RequestParam("chefCommentDate") Date chefCommentDate,
            @RequestParam("empId") Integer empId,
            @RequestParam ("memId")Integer memId,
            @RequestParam("studentCommentContent") String studentCommentContent,
            @RequestParam("studentCommentDate") Date studentCommentDate) {
        System.out.println("chefCommentContent: " + chefCommentContent);

    CourseStudentComment courseStudentComment=courseStudentCommentService.getById(courseStudentCommentId);
    courseStudentComment.setStudentCommentId(courseStudentCommentId);
    courseStudentComment.setCourseId(courseId);
    courseStudentComment.setChefCommentContent(chefCommentContent);
    courseStudentComment.setChefCommentDate(chefCommentDate);
    courseStudentComment.setEmpId(empId);
    courseStudentComment.setMemId(memId);
    courseStudentComment.setStudentCommentContent(studentCommentContent);
    courseStudentComment.setStudentCommentDate(studentCommentDate);
    courseStudentCommentService.update(courseStudentComment);
    return ResponseEntity.ok().body("修改成功");
    }


//前台新增留言
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
//前台顯示留言
    @GetMapping("/replyAllComment")
    public String replyAllComment(@RequestParam Integer courseId, Model model){
        List<CourseCommentDTO>comment = courseStudentCommentService.findAllCommentByCourseId(courseId);
        Course course=courseService.getCourseById(courseId);
        Chef chef=chefService.getChefById(course.getEmpId());
        Integer empId=chef.getEmpId();
        String  empName=chefService.getEmpNameById(empId);
        List<CourseImage> list=courseImageService.getCourseVideoById(courseId);
        model.addAttribute("course",course);
        model.addAttribute("empName",empName);
        model.addAttribute("courseImage",list);
        model.addAttribute("chef",chef);
        model.addAttribute("courseComment",comment);
        comment.forEach(System.out::println);
        return "/front-end/courseStudentComment/coursecomment";
    }

//後臺主廚回覆留言


    //後台刪除留言
    @GetMapping("/CourseStudentComment/deletecomment")
    public  ResponseEntity<?> deletecomment(@RequestParam Integer courseStudentCommentId){
        courseStudentCommentService.delete(courseStudentCommentId);
        System.out.println(courseStudentCommentId);
        return ResponseEntity.ok().build();
    }
}

