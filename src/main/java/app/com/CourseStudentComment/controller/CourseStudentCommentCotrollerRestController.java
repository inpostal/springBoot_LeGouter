//package app.com.CourseStudentComment.controller;
//
//import app.com.emp.vo.Employee;
//import app.com.member.vo.Members;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpSession;
//@RestController
//public class CourseStudentCommentCotrollerRestController {
//    @GetMapping("/check/emp/login")
//    public Employee getEmp(HttpSession session){
//        Employee emp = (Employee) session.getAttribute("emp");
//        System.out.println(emp);
//        return emp;
//    }
//    @GetMapping("/check/user/login")
//    public Members getUser(HttpSession session){
//        Members user = (Members) session.getAttribute("user");
//        System.out.println(user);
//        return user;
//    }
//
//}
