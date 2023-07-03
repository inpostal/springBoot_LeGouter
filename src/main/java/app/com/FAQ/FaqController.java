package app.com.FAQ;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaqController {
@GetMapping("/front/FAQ")
    public String faq(){
    return ("/front-end/FAQ/FAQ");
}
}
