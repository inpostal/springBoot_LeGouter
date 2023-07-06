package app.com.course.controller;

import app.com.coupon.service.CouponService;
import app.com.coupon.vo.CheckOutDto;
import app.com.course.service.ChefService;
import app.com.course.service.CourseImageService;
import app.com.course.service.CourseService;
import app.com.course.vo.*;
import app.com.emp.vo.Employee;
import app.com.member.service.MemberService;
import app.com.member.vo.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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

    @Autowired
    CouponService service;

    // 列出所有課程頁面
    @GetMapping("/course/allcrs")
    public String allCrs(HttpSession session) {
        Employee emp = (Employee) session.getAttribute("emp");
        if (emp == null) {
            return "redirect:/employee/login";
        } else {
            if (emp.getEmpClassify() != 2) {
                return "redirect:/employee/data";
            }
            return "/back-end/course/CourseMgmt";
        }
    }

    // 單個課程編輯頁面
    @GetMapping("/course/edit/{courseId}")
    public String editCoursePage(@PathVariable Integer courseId, Model model,
                                 HttpSession session) {
        Employee emp = (Employee) session.getAttribute("emp");
        if (emp == null) {
            return "redirect:/employee/login";
        }
        Course course = courseService.getCourseById(courseId);
        List<CourseImage> list = courseVideoService.getCourseVideoById(courseId);


        courseService.update(course);
        model.addAttribute("course", course);
        model.addAttribute("videos", list);
        model.addAttribute("courseImageUrl", "/course/img/" + courseId);

        return "/back-end/course/CourseEdit";
    }


    // 單個課程編輯儲存
    @PostMapping("/course/update")
    public ResponseEntity<?> updateCourse(@RequestParam Integer courseId,
                                          @RequestParam String courseName,
                                          @RequestParam String courseContent,
                                          @RequestParam Integer coursePrice,
                                          @RequestParam(value = "courseImg", required = false) MultipartFile courseImg,
                                          @RequestParam(value = "videoFiles", required = false) MultipartFile[] videoFiles,
                                          @RequestParam(value = "videoNames", required = false) String[] videoNames) {

        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        course.setCourseName(courseName);
        course.setCourseContent(courseContent);
        course.setCoursePrice(coursePrice);
        course.setCourseStatus(0);

        if (courseImg != null && !courseImg.isEmpty()) {
            try {
                course.setCourseVideo(courseImg.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        courseService.update(course);

        // Save new CourseImages
        if (videoFiles != null && videoNames != null && videoFiles.length == videoNames.length) {
            for (int i = 0; i < videoFiles.length; i++) {
                CourseImage courseImage = new CourseImage();
                courseImage.setCourseId(courseId);

                try {
                    courseImage.setCourseImg(videoFiles[i].getBytes());
                    courseImage.setCourseImgName(videoNames[i]);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                courseVideoService.add(courseImage);
            }
        }

        return ResponseEntity.ok().build();
    }


    //單個課程查看頁面(可下架)
    @GetMapping("/course/coursereview/{courseId}")
    public String courseReview(@PathVariable Integer courseId, Model model, HttpSession session) {
        Employee emp = (Employee) session.getAttribute("emp");
        if (emp == null) {
            return "redirect:/employee/login";
        }
        Course course = courseService.getCourseById(courseId);
        List<CourseImage> list = courseVideoService.getCourseVideoById(courseId);
        model.addAttribute("course", course);
        model.addAttribute("videos", list);

        return "/back-end/course/CourseReview";
    }

    //課程修改重新上架
    @GetMapping("/course/courserestart/{courseId}")
    public String courseRestart(@PathVariable Integer courseId, Model model, HttpSession session) {
        Employee emp = (Employee) session.getAttribute("emp");
        if (emp == null) {
            return "redirect:/employee/login";
        }
        Course course = courseService.getCourseById(courseId);
        List<CourseImage> list = courseVideoService.getCourseVideoById(courseId);
        model.addAttribute("course", course);
        model.addAttribute("videos", list);

        return "/back-end/course/CourseRestart";
    }

    //課程新增頁面
    @GetMapping("/course/addcourse")
    public String addCoursePage(HttpSession session) {
        Employee emp = (Employee) session.getAttribute("emp");
        if (emp == null) {
            return "redirect:/employee/login";
        }
        return "/back-end/course/AddCourse";  // 返回 addCourse.html 页面
    }

    // 新增課程回資料庫
    @PostMapping("/course/add")
    public ResponseEntity<?> addCourse(@RequestParam String courseName,
                                       @RequestParam String courseContent,
                                       @RequestParam Integer coursePrice,
                                       @RequestParam MultipartFile courseImg,
                                       @RequestParam MultipartFile[] videoFiles,
                                       @RequestParam String[] videoNames,
                                       HttpSession session


    ) {
        Employee emp = (Employee) session.getAttribute("emp");

        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseContent(courseContent);
        course.setCoursePrice(coursePrice);
        course.setCourseStatus(0);
        course.setEmpId(emp.getEmpId());


        try {
            course.setCourseVideo(courseImg.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Course addCourse = courseService.addCourse(course);

        for (int i = 0; i < videoFiles.length; i++) {
            CourseImage courseImage = new CourseImage();
            courseImage.setCourseId(addCourse.getCourseId());
            try {
                courseImage.setCourseImg(videoFiles[i].getBytes());
                courseImage.setCourseImgName(videoNames[i]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            courseVideoService.add(courseImage);
        }

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


    // 刪除課程 (用不到XD)
    @DeleteMapping("/course/delete/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer courseId) {
        courseService.delete(courseId);
        return ResponseEntity.ok().build();
    }

    //課程審核清單頁面
    @GetMapping("/course/coursechecklist")
    public String courseCheckList(HttpSession session) {
        Employee emp = (Employee) session.getAttribute("emp");
        if (emp == null) {
            return "redirect:/employee/login";
        } else if (emp.getEmpClassify() != 2) {
            return "/back-end/course/CourseCheckList";
        }
        return "/back-end/Employee/EmpData";
    }

    //課程審核評論新增
    @PostMapping("/course/addcomment")
    public ResponseEntity<?> addCourseComment(@RequestParam("courseId") Integer courseId,
                                              @RequestParam("courseComment") String courseComment) {
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
    @GetMapping("/course/checkupdate/{courseId}")
    public String checkUpdate(@PathVariable Integer courseId, Model model, HttpSession session) {
        Employee emp = (Employee) session.getAttribute("emp");
        if (emp == null) {
            return "redirect:/employee/login";
        }
        Course course = courseService.getCourseById(courseId);
        List<CourseImage> videos = courseVideoService.getCourseVideoById(courseId);
        model.addAttribute("course", course);
        model.addAttribute("videos", videos);
        return "/back-end/course/CourseCheckUpdate";
    }


    //單個課程審核確認頁面(不能編輯)
    @GetMapping("/course/coursecheck/{courseId}")
    public String courseCheck(@PathVariable Integer courseId, Model model, HttpSession session) {
        Employee emp = (Employee) session.getAttribute("emp");
        if (emp == null) {
            return "redirect:/employee/login";
        }
        Course course = courseService.getCourseById(courseId);
        List<CourseImage> list = courseVideoService.getCourseVideoById(courseId);
        model.addAttribute("course", course);
        model.addAttribute("videos", list);

        return "/back-end/course/CourseCheck";
    }

    //前台課程
    @GetMapping("/course/courses")
    public String coursesPage(HttpSession session, Model model) {
        return "/front-end/course/Course";
    }

    //前台單個課程
    @GetMapping("/course/singlecourse")
    public String singleCoursePage(@RequestParam Integer courseId, Model model) {
        Course course = courseService.getCourseById(courseId);

        Chef chef = chefService.getChefById(course.getEmpId());
        Integer empId = chef.getEmpId();
        String empName = chefService.getEmpNameById(empId);
        List<CourseImage> list = courseVideoService.getCourseVideoById(courseId);


        model.addAttribute("course", course);
        model.addAttribute("chef", chef);
        model.addAttribute("empName", empName);
        model.addAttribute("videos", list);
        return "/front-end/course/SingleCourse";
    }

    //影片專用
    @GetMapping("/course/{courseId}/{videoId}")
    public ResponseEntity<?> getCourseVideoById(@PathVariable Integer courseId, @PathVariable Integer videoId) {
        List<CourseImage> courseVideos = courseVideoService.getCourseVideoById(courseId);
        if (courseVideos != null && !courseVideos.isEmpty()) {
            for (CourseImage courseVideo : courseVideos) {
                if (courseVideo.getCourseImgId() == videoId) {
                    byte[] video = courseVideo.getCourseImg();
                    ByteArrayResource resource = new ByteArrayResource(video);
                    return ResponseEntity.ok()
                            .contentType(MediaType.parseMediaType("video/mp4"))
                            .body(resource);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    //影片刪除
    @PostMapping("/course/deleteVideo")
    public ResponseEntity<?> deleteVideo(@RequestParam Integer videoId) {
        // 根据videoId从数据库中获取视频记录
        CourseImage video = courseVideoService.getVideoById(videoId);

        if (video == null) {
            return ResponseEntity.notFound().build();
        }

        courseVideoService.delete(video);

        // 返回成功响应
        return ResponseEntity.ok().build();
    }

    //結帳資料進資料庫
    @PostMapping("/course/checkoutdata")
    public ResponseEntity<?> checkoutData(@RequestBody CheckoutDTO dto, HttpSession session) {
        System.out.println(dto);
        courseService.checkout(dto);
        Members user = (Members) session.getAttribute("user");
        if (dto.getCpId() != 0) {
            service.updateCpStatus(user.getMemberId(), dto.getCpId());
        }
        return ResponseEntity.ok().build();
    }

    //課程結帳資料頁面顯示
    @GetMapping("/course/coursecheckout")
    public String courseCheckout(HttpSession session,
                                 @RequestParam Integer courseId,
                                 Model model) {
        Members user = (Members) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("location", "/course/coursecheckout?courseId=" + courseId);

            return "redirect:/login";
        }
        CheckoutDTO dto = courseService.getCheckoutData(user.getMemberId(), courseId);
        service.deleteFollowList(user.getMemberId(), courseId);
        List<CheckOutDto> cp = service.findAllMemCourseCp(courseId, user.getMemberId());
        model.addAttribute("checkout", dto);
        model.addAttribute("cplist", cp);
        return "/front-end/course/CourseCheckout";
    }

    //主廚資料頁面顯示
    @GetMapping("/course/chef")
    public String courseChef(HttpSession session,
                             Model model) {
        Employee emp = (Employee) session.getAttribute("emp");
        if (emp == null) {
            return "redirect:/employee/login";
        } else {
            if (emp.getEmpClassify() != 2) {
                return "redirect:/employee/data";
            }
            Chef chef = chefService.findByEmpId(emp.getEmpId());
            ChefInfoDTO dto = courseService.getChefData(emp.getEmpId(), chef.getChefId());
            model.addAttribute("chef", dto);
            return "/back-end/course/Chef";
        }
    }

    //主廚資料更新
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

    //確認重複購買
    @PostMapping("/checkcoursepurchase")
    public ResponseEntity<?> checkCoursePurchase(@RequestBody CheckoutDTO request) {
        try {
            boolean isCoursePurchased = courseService.isCoursePurchased(request.getCourseId(), request.getMemberId());
            if (isCoursePurchased) {
                return ResponseEntity.ok("買過");
            } else {
                return ResponseEntity.ok("沒買");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
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

//影片專用
//    @GetMapping("/course/{courseId}/videos")
//    public ResponseEntity<?> getCourseVideos(@PathVariable Integer courseId) {
//        List<CourseImage> courseVideos = courseVideoService.getCourseVideoById(courseId);
//
//        if (courseVideos != null && !courseVideos.isEmpty()) {
//            List<CourseImage> resources = new ArrayList<>();
//            for (CourseImage courseVideo : courseVideos) {
//                byte[] videoBytes = courseVideo.getCourseImage();
//                ByteArrayResource resource = new ByteArrayResource(videoBytes);
//                resources.add(courseVideos);
//            }
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.parseMediaType("video/mp4"));
//
//            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"videos.zip\"");
//
//            return ResponseEntity.ok().headers(headers).body(resources);
//        }
//
//        return ResponseEntity.notFound().build();
//    }

//單個影片
//    @GetMapping("/course/{courseId}/{courseVideoId}")
//    public ResponseEntity<?> getCourseVideoById(@PathVariable Integer courseId, @PathVariable Integer courseVideoId) {
//        CourseImage courseVideoById = courseVideoService.getCourseImageById(courseVideoId);
//        byte[] video = courseVideoById.getCourseImage();
//        ByteArrayResource resource = new ByteArrayResource(video);
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType("video/mp4"))
//                .body(resource);
//    }

//影片專用thymleaf
//    @GetMapping("/course/{courseId}/videos")
//    public String getCourseVideos(@PathVariable Integer courseId, Model model) {
//        List<CourseImage> courseVideos = courseVideoService.getCourseVideoById(courseId);
//        model.addAttribute("videos", courseVideos);
//        return "/back-end/course/"; // 返回视图文件名
//    }

