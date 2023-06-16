package app.com.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;

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
        session.removeAttribute("user");
        return "redirect:/login";
    }
    @GetMapping("/memberData")
    public String memberData(HttpSession session, RedirectAttributes redirectAttributes){
        if (session.getAttribute("user")!=null){
            return "front-end/member/MemberData";
        }else {
            redirectAttributes.addFlashAttribute("pleaseLogin", "請先登入!");
            return "redirect:/login";
        }
    }
}
