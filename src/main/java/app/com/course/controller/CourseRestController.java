package app.com.course.controller;

import app.com.course.service.CourseService;
import app.com.course.vo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//處理ajax請求
//重導用 只需要一個
@RestController
public class CourseRestController {
    @Autowired
    CourseService service;

    @GetMapping("/course/getall")
    public List<Course> allCourseList(){
        return service.getCourse();
    };


}
