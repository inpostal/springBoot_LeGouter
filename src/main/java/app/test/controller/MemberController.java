package app.test.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
