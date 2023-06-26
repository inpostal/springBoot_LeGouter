package app.com.course.controller;

import app.com.course.service.ChefService;
import app.com.course.service.CourseImageService;
import app.com.course.service.CourseService;
import app.com.course.vo.*;
import app.com.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

// 窗口 負責前後台都可以
@Controller
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    ChefService chefService;

    @Autowired
    CourseImageService courseVideoService;

    @Autowired
    MemberService memberService;

    // 列出所有課程頁面
    @GetMapping("/course/allcrs")
    public String allCrs() {
        return "/back-end/course/CourseMgmt";
    }

    // 單個課程編輯頁面
    @GetMapping("/course/edit")
    public String editCoursePage(@RequestParam Integer courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        List<CourseImage> list = courseVideoService.getCourseVideoById(courseId);

        courseService.update(course);
        model.addAttribute("course", course);
        model.addAttribute("list", list);

        return "/back-end/course/CourseEdit";
    }

    // 單個課程編輯儲存
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
        course.setCourseStatus(0);

        try {
            course.setCourseVideo(courseImg.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        courseService.update(course);

        return ResponseEntity.ok().build();
    }

    //單個課程查看頁面(可下架)
    @GetMapping("/course/coursereview")
    public String courseReview(@RequestParam Integer courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        List<CourseImage> list = courseVideoService.getCourseVideoById(courseId);
        model.addAttribute("course", course);
        model.addAttribute("list", list);

        return "/back-end/course/CourseReview";
    }

    //課程新增頁面
    @GetMapping("/course/addcourse")
    public String addCoursePage() {
        return "/back-end/course/AddCourse";  // 返回 addCourse.html 页面
    }

    // 新增課程回資料庫
    @PostMapping("/course/add")
    public ResponseEntity<?> addCourse(@RequestParam String courseName,
                                       @RequestParam String courseContent,
                                       @RequestParam Integer coursePrice,
                                       @RequestParam MultipartFile courseImg,
                                       @RequestParam MultipartFile courseVideo

    ) {

        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseContent(courseContent);
        course.setCoursePrice(coursePrice);
        course.setCourseStatus(0);
        course.setEmpId(1);


        try {
            course.setCourseVideo(courseImg.getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Course addCourse = courseService.addCourse(course);
        //新增後需要在加影片, 拿新增後的id再新增影片
        CourseImage video = new CourseImage();
        //給他id
        video.setCourseId(addCourse.getCourseId());
        try {
            video.setCourseImage(courseVideo.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //放影片進去
        courseVideoService.add(video);
        return ResponseEntity.ok().build();
    }

    // 照片
    @GetMapping("/course/img/{courseId}")
    public ResponseEntity<?> getCourseImageById(@PathVariable Integer courseId) {
        Course course = courseService.getCourseById(courseId);

        byte[] img = course.getCourseVideo();

        ByteArrayResource resource = new ByteArrayResource(img);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_GIF)
                .body(resource);
    }

    //課程修改重新上架
    @GetMapping("/course/courserestart")
    public String courseRestart(@RequestParam Integer courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        List<CourseImage> list = courseVideoService.getCourseVideoById(courseId);
        model.addAttribute("course", course);
        model.addAttribute("list", list);
        return "/back-end/course/CourseRestart";
    }

    // 刪除課程 (用不到XD)
    @DeleteMapping("/course/delete/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer courseId) {
        courseService.delete(courseId);
        return ResponseEntity.ok().build();
    }

    //課程審核清單頁面
    @GetMapping("/course/coursechecklist")
    public String courseCheckList() {
        return "/back-end/course/CourseCheckList";
    }

    //課程審核評論新增
    @PostMapping("/course/addcomment")
    public ResponseEntity<?> addCourseComment(@RequestParam Integer courseId,
                                              @RequestParam String courseComment) {

        Course course = courseService.getCourseById(courseId);
        course.setCourseComment(courseComment);
        courseService.update(course);
        return ResponseEntity.ok().build();

    }

    // 下架課程 狀態=3
    @PostMapping("/course/cancelcourse")
    public ResponseEntity<?> cancelCourse(@RequestParam Integer courseId) {

        Course course = courseService.getCourseById(courseId);

        // 直接將課程狀態設定為3
        course.setCourseStatus(3);

        courseService.update(course);

        return ResponseEntity.ok().build();
    }

    // 上架課程 狀態=1
    @PostMapping("/course/coursestart")
    public ResponseEntity<?> courseStart(@RequestParam Integer courseId) {

        Course course = courseService.getCourseById(courseId);

        // 直接將課程狀態設定為1
        course.setCourseStatus(1);

        courseService.update(course);

        return ResponseEntity.ok().build();
    }

    // 退件課程 狀態=2
    @PostMapping("/course/coursereturn")
    public ResponseEntity<?> courseReturn(@RequestParam Integer courseId) {

        Course course = courseService.getCourseById(courseId);

        // 直接將課程狀態設定為1
        course.setCourseStatus(2);

        courseService.update(course);

        return ResponseEntity.ok().build();
    }

    // 單個審核頁面
    @GetMapping("/course/checkupdate")
    public String checkUpdate(@RequestParam Integer courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        List<CourseImage> list = courseVideoService.getCourseVideoById(courseId);
        model.addAttribute("course", course);
        model.addAttribute("list", list);
        return "/back-end/course/CourseCheckUpdate";
    }

    //單個課程審核確認頁面(不能編輯)
    @GetMapping("/course/coursecheck")
    public String courseCheck(@RequestParam Integer courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        List<CourseImage> list = courseVideoService.getCourseVideoById(courseId);
        model.addAttribute("course", course);
        model.addAttribute("list", list);

        return "/back-end/course/CourseCheck";
    }

    //前台課程
    @GetMapping("/course/courses")
    public String coursesPage() {
        return "/front-end/course/Course";
    }

    //前台單個課程
    @GetMapping("/course/singlecourse")
    public String singleCoursePage(@RequestParam Integer courseId, Model model) {
        Course course = courseService.getCourseById(courseId);

        Chef chef = chefService.getChefById(course.getEmpId());

        model.addAttribute("course", course);
        model.addAttribute("chef", chef);
        return "/front-end/course/SingleCourse";
    }

    //影片專用
    @GetMapping("/course/{courseId}/{courseVideoId}")
    public ResponseEntity<?> getCourseVideoById(@PathVariable Integer courseId, @PathVariable Integer courseVideoId) {
        CourseImage courseVideoById = courseVideoService.getCourseImageById(courseVideoId);
        byte[] video = courseVideoById.getCourseImage();
        ByteArrayResource resource = new ByteArrayResource(video);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("video/mp4"))
                .body(resource);

    }

    //結帳資料進資料庫
    @PostMapping("/course/checkoutdata")
    public ResponseEntity<?> checkoutData(@RequestBody CheckoutDTO dto) {
        courseService.checkout(dto);
        return ResponseEntity.ok().build();
    }

    //課程資料頁面顯示
    @GetMapping("/course/coursecheckout")
    public String courseCheckout(@RequestParam Integer memberId,
                                 @RequestParam Integer courseId,
                                 Model model) {
        CheckoutDTO dto = courseService.getCheckoutData(memberId, courseId);
        model.addAttribute("checkout", dto);
        return "/front-end/course/CourseCheckout";
    }

    //主廚資料頁面顯示 要提供empid跟chefid：?empId=1&chefId=1
    @GetMapping("/course/chef")
    public String courseChef(@RequestParam Integer empId,
                             @RequestParam Integer chefId,
                             Model model) {
        ChefInfoDTO dto = courseService.getChefData(empId, chefId);
        model.addAttribute("chef", dto);
        return "/back-end/course/Chef";
    }

    @PostMapping("/course/updateChefInfo")
    public ResponseEntity<?> updateChefInfo(@RequestParam Integer chefId,
                                            @RequestParam String chefInfo) {
        Chef updatedChef = courseService.updateChefInfo(chefId, chefInfo);

        if (updatedChef == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chef with id " + chefId + " not found.");
        } else {
            return ResponseEntity.ok().build();
        }
    }


}


//    @GetMapping("/course/singlecrs")
//    public String singleCrs(@RequestParam Integer courseId, Model model) {
//        Course course = courseService.getCourseById(courseId);
//        model.addAttribute("course", course);
//        return "CourseEdit";
//    }

//    @GetMapping("/course/crsreview")
//    public String crsReview(@RequestParam Integer courseId, Model model) {
//        Course course = courseService.getCourseById(courseId);
//        model.addAttribute("course", course);
//        return "CourseCheck";
//    }

//追蹤頁面
//    @GetMapping("/course/coursewishlist")
//    public String courseWishlist() {
//        return "/front-end/course/CourseWishlist";
//    }


//主廚頁面
//    @GetMapping("/course/chef")
//    public String chefPage() {
//        return "/back-end/course/Chef";
//    }