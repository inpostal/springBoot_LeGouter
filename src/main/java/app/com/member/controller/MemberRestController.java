package app.com.member.controller;

import app.com.member.service.MemberService;
import app.com.member.vo.Members;
import app.com.member.vo.MembersDTO;
import app.com.member.vo.MembersRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MemberRestController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/member/memberData/{id}")
    public MembersDTO getMemberById(@PathVariable Integer id){
        return  memberService.getMemberById(id);
    }

    @PostMapping("/member/login")
    public ResponseEntity<?> login(@RequestBody  Members members, HttpSession session){
        Members membersInDB = memberService.login(members.getMemberAccount(), members.getMemberPassword());
        if (membersInDB != null) {
            session.setAttribute("user", membersInDB);
            return ResponseEntity.ok("Logged in successfully");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/checkEmail")
    public Map<String, Boolean> checkEmail(@RequestParam String email){
        Boolean isExist  = memberService.findByEmail(email) != null;
        Map<String, Boolean> response = new HashMap<>();
        response.put("isExist", isExist);
        return response;
    }

    @PostMapping("/checkAccount")
    public Map<String, Boolean> checkAccount(@RequestParam String account){
        Boolean isExist = memberService.findByAccount(account) != null;
        Map<String, Boolean> response = new HashMap<>();
        response.put("isExist", isExist);
        return response;
    }

    @PostMapping("/register")
    public Map<String, Boolean> registerUser(@RequestBody MembersRegisterDTO dto) {
        Boolean success = memberService.memberRegister(dto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", success);
        return response;
    }




}
