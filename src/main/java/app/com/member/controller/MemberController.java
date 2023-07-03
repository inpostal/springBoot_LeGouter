package app.com.member.controller;

import app.com.member.service.MemberService;
import app.com.member.vo.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MemberController {
    @Autowired
    private MemberService service;

    @GetMapping("/resetpassword")
    public String resetPasswordPage(@RequestParam String token, Model model) {
        Members members = service.findByToken(token);
        if (members == null) {
            return "redirect:/LeGouter";
        } else {
            model.addAttribute("reset", members);
            return "/front-end/member/ResetPWD";
        }
    }

    @GetMapping("/forgotpassword")
    public String forGotPasswordPage() {
        return "/front-end/member/ForgotPWD";
    }


    @GetMapping("/header")
    public String getHeader() {
        return "front-end/components/Header";
    }

    @GetMapping("/footer")
    public String getFooter() {
        return "front-end/components/Footer";
    }

    @GetMapping("/navbar")
    public String getNavBar() {
        return "front-end/components/NavBar";
    }

    @GetMapping("/carousel")
    public String getCarousel() {
        return "front-end/components/Carousel";
    }

    @GetMapping("/copyright")
    public String getCopyright() {
        return "front-end/components/Copyright";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "front-end/member/MemberLogin";
    }

    @GetMapping("/register")
    public String memberRegister() {
        return "front-end/member/MemberRegister";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/member/data")
    public String memberData(HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("user") != null) {
            return "front-end/member/MemberData";
        } else {
            redirectAttributes.addFlashAttribute("pleaseLogin", "請先登入!");
            return "redirect:/login";
        }
    }

    @GetMapping("/member/data/edit")
    public String editMemberData(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        Members user = (Members) session.getAttribute("user");
        if (user != null) {
            String address = user.getMemberAddress();
            String[] split = address.split(",");

            model.addAttribute("city", split[0]);
            model.addAttribute("district", split[1]);
            model.addAttribute("address", split[2]);

            return "front-end/member/MemberDataEdit";
        } else {
            redirectAttributes.addFlashAttribute("pleaseLogin", "請先登入!");
            return "redirect:/login";
        }
    }

    // 後台會員資料列表
    @GetMapping("/member/data/list")
    public String memberDataList(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        if (session.getAttribute("emp") != null) {
            List<Members> list = service.getAllMember();
            model.addAttribute("memberList", list);
            return "/back-end/Member/MemberList";
        } else {
            redirectAttributes.addFlashAttribute("pleaseLogin", "請先登入!");
            return "/back-end/Employee/EmpLogin";
        }
    }

    // 後台會員資料編輯
    @GetMapping("/member/data/edit/{memberId}")
    public String memberDataEdit(@PathVariable("memberId") Integer memberId, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        System.out.println(memberId);
        if (session.getAttribute("emp") != null) {
            model.addAttribute("member", service.getMemberById(memberId));
            return "/back-end/Member/MemberDataEdit";
        } else {
            redirectAttributes.addFlashAttribute("pleaseLogin", "請先登入!");
            return "/back-end/Employee/EmpLogin";
        }
    }

    // 處理登入
    @PostMapping("/member/login")
    @ResponseBody
    public Map<String, String> login(@RequestBody Members members, HttpSession session) {
        Map<String, String> result = new HashMap<>();
        Members membersInDB = service.login(members.getMemberAccount(), members.getMemberPassword());

        if (membersInDB != null) {
            session.setAttribute("user", membersInDB);
            String location = (String) session.getAttribute("location");
            if (location != null) {
                result.put("location", location);
                session.removeAttribute("location");
                return result;
            } else {
                result.put("location", "/LeGouter");
                return result;
            }
        } else {
            result.put("success", "failed");
            return result;
        }
    }
}
