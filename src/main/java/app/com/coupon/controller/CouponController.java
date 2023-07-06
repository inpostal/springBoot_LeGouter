package app.com.coupon.controller;

import app.com.coupon.service.CouponService;
import app.com.coupon.vo.*;
import app.com.course.vo.Course;
import app.com.emp.vo.Employee;
import app.com.member.vo.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CouponController {
    @Autowired
    private CouponService service;

    //領優惠券
    @GetMapping("/get/coupon")
    public String GetCoupon(HttpSession session) {
        Members user = (Members) session.getAttribute("user");
        if (user != null){
            return "front-end/Coupon/GetCoupon";
        }else{
            session.setAttribute("location", "/get/coupon");
            return "/front-end/member/MemberLogin";
        }
    }

//    @GetMapping("/check/course/viewCp/{price}")
//    @ResponseBody
//    public List<CheckOutDto> checkCourseCoupon(@PathVariable Integer price,HttpSession session){
//        Members user = (Members) session.getAttribute("user");
//        Integer memberId = user.getMemberId();
//        return service.findAllMemCourseCp(price, memberId);
//    }


//    @PostMapping("check/dessert/viewCp/{price}")
//    @ResponseBody
//    public List<CheckOutDto> checkDessertCoupon(@PathVariable Integer price,HttpSession session){
//        Members user = (Members) session.getAttribute("user");
//        Integer memberId = user.getMemberId();
//        return service.findAllMemDessertCp(price, memberId);
//    }


    //結帳按鈕-改變會員優惠券使用狀態
    @PostMapping("/alter/cpStatus")
    @ResponseBody
    public void alterCpStatus(@RequestParam Integer cpId, HttpSession session){
        Members user = (Members) session.getAttribute("user");
        service.updateCpStatus(user.getMemberId(), cpId);
    }

    //先連到課程網頁再準備加入追蹤
    @GetMapping("/add/follow")
    public String addCourseFollow(){
            return "front-end/Coupon/CourseAddFollow";
    }

//加入追蹤課程按鈕-提出請求,發送course_id,mem_id加入資料庫中
    @PostMapping("/add/courseFollow/{courseId}")
    @ResponseBody
    public void btnAddCourseFollow(@PathVariable Integer courseId, HttpSession session){
        Members user = (Members) session.getAttribute("user");
        if (user != null) {
            service.addCourseFollow(user.getMemberId(), courseId);
        }
    }

//    @PostMapping("/add/courseFollow")
//    @ResponseBody
//    public void btnAddCourseFollow(@RequestBody Course courseId, HttpSession session){
//        Members user = (Members) session.getAttribute("user");
//        service.addCourseFollow(user.getMemberId(), courseId.getCourseId());
//    }

    @GetMapping("/check/loginStatus")
    @ResponseBody
    public ResponseEntity<?> checkLoginStatus(HttpSession session){
        Members user = (Members) session.getAttribute("user");
        if (user==null){
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity.ok().build();
        }
    }

//刪除按鈕-追蹤清單課程
//    @PostMapping("/delete/follow/list/{courseId}")
//    @ResponseBody
//    public Map<String, Boolean> handleDeleteFollowList(@PathVariable Integer courseId, HttpSession session) {
//        Map<String, Boolean> result = new HashMap<>();
//        Members user = (Members) session.getAttribute("user");
//        LoveCourse loveCourse = new LoveCourse();
//        loveCourse.setCourseId(courseId);
//        loveCourse.setMemId(user.getMemberId());
//        service.deleteFollowList(loveCourse);
//
//        result.put("isSuccess", true);
//        return result;
//    }

//    @GetMapping("/delete/follow/list/{courseId}/{memId}")
//    @ResponseBody
//    public void deleteFollowList(@PathVariable Integer courseId, @PathVariable Integer memId){
//        service.deleteFollowList(memId, courseId);
//    }
    //單頁
    //領取更新會員優惠券時,收ajax傳送資料用
    @PostMapping("/coupon/memCp")
    public ResponseEntity<?> addMemCoupon(HttpSession session, @RequestBody MembersCpId memCp) {
        Members user = (Members) session.getAttribute("user");
        memCp.setMemId(user.getMemberId());
        MembersCp cp = service.addMemCp(memCp);
        return ResponseEntity.ok().build();
    }

    //cp管理頁面
    @GetMapping("/coupon/master")
    public String couponManage(Model model, HttpSession session) {
        Employee emp = (Employee) session.getAttribute("emp");
        if (emp == null){
            return "/back-end/Employee/EmpLogin";
//            session.setAttribute("location","/coupon/master");
        } else {
            List<CouponType> list = service.getAllCouponType();
            model.addAttribute("couponList", list);
            return "/back-end/Coupon/CouponMaster";
        }
    }

    //新增cp
    @GetMapping("/coupon/add")
    public String couponAdd(Model model, HttpSession session) {
        Employee emp = (Employee) session.getAttribute("emp");
        if (emp == null){
            return "/back-end/Employee/EmpLogin";
        } else {
            List<CouponType> list = service.getAllCouponType();
            model.addAttribute("couponList", list);
            return "/back-end/Coupon/CouponAdd";
            }
        }

    //單頁
    //新增cp時,收ajax傳送資料用
    @PostMapping("/coupon/addCp")
    public ResponseEntity<?> addCoupon(@RequestBody CouponType coupon) {
        CouponType cp = service.addCpType(coupon);
        if (cp != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //編輯cp
    @GetMapping("/coupon/edit/{cpid}")
    public String couponEdit(@PathVariable Integer cpid, Model model, HttpSession session) {
        Employee emp = (Employee) session.getAttribute("emp");

        if (emp == null){
            return "/back-end/Employee/EmpLogin";
        } else {
            CouponType couponInDb = service.getById(cpid);
            model.addAttribute("cp", couponInDb);
            return "/back-end/Coupon/CouponEdit";
        }
    }

    //編輯(更新)cp時,收ajax傳送資料用
    @PostMapping("/coupon/editCp")
    public ResponseEntity<?> editCoupon(@RequestBody CouponType coupon) {
        CouponType cp = service.editCpType(coupon);
        return ResponseEntity.ok().build();
    }

    //課程頁面領取cp
    //需登入會員狀態
    @GetMapping("/coupon/shopping")
    public String couponShopping(HttpSession session, Model model) {

        Members user = (Members) session.getAttribute("user");
        if (user == null) {
            //未登入先導至登入頁面
//            session.setAttribute("location", "/coupon/shopping");
//            return "/front-end/member/MemberLogin";
            return "/front-end/Coupon/Shopping";
        } else {

            List<CouponType> list = service.getCourseCp(user.getMemberId());
            model.addAttribute("courseCp", list);
            return "/front-end/Coupon/Shopping";
        }
    }

    //優惠券領取專區-ajax渲染甜點頁面優惠券
    @GetMapping("/coupon/getDessert")
    @ResponseBody
    public List<CouponType> getDessertCp(HttpSession session){
        Members user = (Members) session.getAttribute("user");
        if (user!=null){
            List<CouponType> list = service.getDessertCp(user.getMemberId());
            return list;
        }
        return null;
    }

    //優惠券領取專區-ajax渲染甜點頁面優惠券
    @GetMapping("/coupon/getCourse")
    @ResponseBody
    public List<CouponType> getCourseCp(HttpSession session){
        Members user = (Members) session.getAttribute("user");
        if (user!=null){
            List<CouponType> list = service.getCourseCp(user.getMemberId());
            return list;
        }
        return null;
    }



    //模擬甜點頁面領取cp
    //需登入會員狀態
    @GetMapping("/coupon/shopping/dessert")
    public String couponShoppingDessert(HttpSession session, Model model) {
        Members user = (Members) session.getAttribute("user");
        if (user == null) {
            //未登入先導至登入頁面
            session.setAttribute("location", "/coupon/shopping/dessert");
            return "/front-end/member/MemberLogin";
        } else {
            List<CouponType> list = service.getDessertCp(user.getMemberId());
            model.addAttribute("courseCp", list);
            return "/front-end/Coupon/ShoppingDessert";
        }
    }

    //顯示會員優惠券
    //需登入會員狀態
    @GetMapping("/coupon/data")
    public String couponView(Model model, HttpSession session) {
        Members members = (Members) session.getAttribute("user");
        if (members == null) {
            //未登入先導至登入頁面
            session.setAttribute("location", "/coupon/data");
            return "/front-end/member/MemberLogin";
        } else {
            //已登入用service去顯示所有優惠券
            List<CouponType> list = service.getAllCouponTypeMem(members.getMemberId());
            model.addAttribute("couponList", list);
            return "/front-end/Coupon/CouponData";
        }
    }


    @GetMapping("/test/add/followList")
    public String testAddFollow(@RequestParam("courseId") Integer courseId){

        return "/front-end/Coupon/SingleCourse1.html";
    }

    //追蹤清單
    @GetMapping("/course/follow")
    public String courseFollowView(Model model, HttpSession session) {
        Members user = (Members) session.getAttribute("user");
        if (user == null) {
            //未登入先導至登入頁面
            session.setAttribute("location", "/course/follow");
            return "/front-end/member/MemberLogin";
        } else {
            List<LoveCourseMemDTO> list = service.findAllByMemberId(user.getMemberId());
            model.addAttribute("followList", list);
            return "/front-end/Coupon/FollowList";
        }
    }
//
//    @GetMapping("/course/coursecheckout?courseId={courseId}")
//    public void checkFollowCourse(@PathVariable ){
//
//    }


    @GetMapping("/check/course")
    public String courseCheckCoupon(){
//    public String courseCheckCoupon(@RequestParam Integer memberId, Model model) {
//        List<CouponType> list = service.getDessertCp(memberId);
//        model.addAttribute("courseCp", list);
        return "/front-end/Coupon/Check";
    }

    //ajax傳會員結帳show優惠券請求
//    @GetMapping("/check/course/viewCp")
//    @ResponseBody
//    public List<ShoppingCpMemDTO> courseCheckCoupon(HttpSession session){
//        Members user = (Members) session.getAttribute("user");
//        Integer memberId = user.getMemberId();
//        List<ShoppingCpMemDTO> result = service.getAllMemCpDto(memberId);
//        return result;
//    }

    @GetMapping("/check/dessert")
    public String dessertCheckCoupon(){
//    public String dessertCheckCoupon(@RequestParam Integer memberId, Model model) {
//        List<CouponType> list = service.getDessertCp(memberId);
//        model.addAttribute("courseCp", list);
        return "/front-end/Coupon/CheckDessert";
    }

//    甜點頁面領取cp2
//    需登入會員狀態
//測試用
    @GetMapping("/check/dessert2")
    public String dessert2CheckCoupon(){
        return "/front-end/Coupon/CheckDessert2";
    }

}

