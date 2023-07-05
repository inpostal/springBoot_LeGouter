package app.com.courseorder.controller;

import app.com.courseorder.service.CourseOrderService;
import app.com.courseorder.vo.CourseOrderDTO;
import app.com.emp.vo.Employee;
import app.com.member.vo.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CourseOrderController {
    @Autowired
    private CourseOrderService service;

    // 前往課程訂單主檔列表頁面
    @GetMapping("/course/order/master/list")
    public String goCourseOrderMasterListPage(Model model, HttpSession session) {
        Employee emp = (Employee) session.getAttribute("emp");
        if (emp!=null){
            List<CourseOrderDTO> list = service.getCourseOrderList();
            model.addAttribute("courseOrderList", list);
            return "/back-end/CourseOrder/CourseOrderMaster";
        }else {
            return "/back-end/Employee/EmpLogin";
        }
    }

    // 前往會員的課程訂單列表頁面
    @GetMapping("/course/order/member")
    public String memberCourseOrderPage(HttpSession session, Model model){
        Members user = (Members) session.getAttribute("user");
        if (user==null){
            return "redirect:/login";
        }
        List<CourseOrderDTO> list = service.getMemberCourseOrderList(user.getMemberId());
        model.addAttribute("courseOrderList", list);
        return "/front-end/CourseOrder/CourseOrder1";
    }
}
