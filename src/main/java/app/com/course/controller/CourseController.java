package app.com.course.controller;

import app.com.course.service.CourseService;
import app.com.course.vo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

// 窗口 負責前後台都可以
@Controller
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/course/allcrs")
    public String allCrs() {
        return "/back-end/course/CourseMgmt";
    }

    @GetMapping("/course/singlecrs")
    public String singleCrs(@RequestParam Integer courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("course", course);
        return "CourseEdit";
    }

    @GetMapping("/course/crsreview")
    public String crsReview(@RequestParam Integer courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("course", course);
        return "/back-end/course/CourseReview";
    }

    @GetMapping("/course/edit")
    public String editCoursePage(@RequestParam Integer courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("course", course);
        return "/back-end/course/CourseEdit";
    }


    @PostMapping("/course/update")
    public ResponseEntity<?> update(@RequestParam Integer courseId,
                                 @RequestParam String courseName,
                                 @RequestParam String courseContent,
                                 @RequestParam Integer coursePrice,
                                 @RequestParam MultipartFile courseImg) {

        Course course = courseService.getCourseById(courseId);


        course.setCourseName(courseName);
        course.setCourseContent(courseContent);
        course.setCoursePrice(coursePrice);

        try {
            course.setCourseVideo(courseImg.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        courseService.update(course);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/course/img/{courseId}")
    public ResponseEntity<?> getCourseImageById(@PathVariable Integer courseId) {
        Course course = courseService.getCourseById(courseId);

        byte[] img = course.getCourseVideo();

        ByteArrayResource resource = new ByteArrayResource(img);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_GIF)
                .body(resource);
    }

    @PostMapping("/course/add")
    public ResponseEntity<?> addCourse(@RequestParam String courseName,
                                       @RequestParam String courseContent,
                                       @RequestParam Integer coursePrice,
                                       @RequestParam MultipartFile courseImg) {

        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseContent(courseContent);
        course.setCoursePrice(coursePrice);
//        System.out.println("courseImg.getBytes() = ");
        try {
//            System.out.println(courseImg.getBytes().length);
            course.setCourseVideo(courseImg.getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        courseService.addCourse(course);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/course/addcourse")
    public String addCoursePage() {
        return "/back-end/course/AddCourse";  // 返回 addCourse.html 页面
    }


    @DeleteMapping("/course/delete/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer courseId) {
        courseService.delete(courseId);
        return ResponseEntity.ok().build();
    }



}


