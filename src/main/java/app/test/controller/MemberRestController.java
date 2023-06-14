package app.test.controller;

import app.test.service.MemberService;
import app.test.vo.Members;
import app.test.vo.MembersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class MemberRestController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/member/memberData/{id}")
    public MembersDTO getMemberById(@PathVariable Integer id){
        return  memberService.getMemberById(id);
    }

    @PostMapping("/member/login")
    public ResponseEntity<?> login(@RequestBody Members members, HttpSession session){
        Members membersInDB = memberService.login(members.getMemberAccount(), members.getMemberPassword());
        if (membersInDB != null) {
            session.setAttribute("user", membersInDB);
            return ResponseEntity.ok("Logged in successfully");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
