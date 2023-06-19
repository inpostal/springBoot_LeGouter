package app.com.member.controller;

import app.com.member.vo.Members;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@Controller
public class MemberController {
    @GetMapping("/LeGouter")
    public String about(){
        return "front-end/FrontIndex";
    }
    @GetMapping("/header")
    public String getHeader(){
        return "front-end/components/Header";
    }
    @GetMapping("/footer")
    public String getFooter(){
        return "front-end/components/Footer";
    }
    @GetMapping("/navbar")
    public String getNavBar(){
        return "front-end/components/NavBar";
    }
    @GetMapping("/carousel")
    public String getCarousel(){
        return "front-end/components/Carousel";
    }
    @GetMapping("/copyright")
    public String getCopyright(){
        return "front-end/components/Copyright";
    }
    @GetMapping("/login")
    public String getLogin(){
        return "front-end/member/MemberLogin";
    }
    @GetMapping("/register")
    public String memberRegister(){ return "front-end/member/MemberRegister";}
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
    @GetMapping("/member/data")
    public String memberData(HttpSession session, RedirectAttributes redirectAttributes){
        if (session.getAttribute("user")!=null){
            return "front-end/member/MemberData";
        }else {
            redirectAttributes.addFlashAttribute("pleaseLogin", "請先登入!");
            return "redirect:/login";
        }
    }
    @GetMapping("/member/data/edit")
    public String editMemberData(HttpSession session, RedirectAttributes redirectAttributes, Model model){
        Members user = (Members) session.getAttribute("user");
        if (user !=null){
            String address = user.getMemberAddress();
            String[] split = address.split(",");

            model.addAttribute("city", split[0]);
            model.addAttribute("district", split[1]);
            model.addAttribute("address", split[2]);

            return "front-end/member/MemberDataEdit";
        }else {
            redirectAttributes.addFlashAttribute("pleaseLogin", "請先登入!");
            return "redirect:/login";
        }
    }

    @GetMapping("/member/data/list")
    public String memberDataList(HttpSession session, RedirectAttributes redirectAttributes){
//        if (session.getAttribute("emp")!=null){
            return "/back-end/Member/MemberList";
//        }else {
//            redirectAttributes.addFlashAttribute("pleaseLogin", "請先登入!");
//            return "/back-end/Employee/EmpLogin";
//        }
    }

    @GetMapping("/member/data/edit/{memberId}")
    public String memberDataEdit(@PathParam("memberId") Integer memberId ,HttpSession session, RedirectAttributes redirectAttributes){
//        if (session.getAttribute("emp")!=null){
            return "/back-end/Member/MemberDataEdit";
//        }else {
//            redirectAttributes.addFlashAttribute("pleaseLogin", "請先登入!");
//            return "/back-end/Employee/EmpLogin";
//        }
    }
}
