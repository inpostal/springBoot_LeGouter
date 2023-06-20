package app.com.common;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConponentController {

    @GetMapping("/get/navbar")
    public String getNavBar(){
        return "/back-end/Conponents/SideBar";
    }

    @GetMapping("/get/header")
    public String getHeader(){
        return "/back-end/Conponents/Header";
    }
}
