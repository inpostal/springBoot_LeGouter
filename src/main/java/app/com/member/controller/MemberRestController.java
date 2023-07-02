package app.com.member.controller;

import app.com.member.service.MemberService;
import app.com.member.service.SendEmailService;
import app.com.member.vo.Members;
import app.com.member.vo.MembersDTO;
import app.com.member.vo.MembersRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class MemberRestController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private SendEmailService emailService;

    @PostMapping("/resetpassword/handle")
    public Map<String, Boolean> handleResetPassword(@RequestBody Members members){
        Map<String, Boolean> response = new HashMap<>();

        Members resetPassword = memberService.resetPassword(members);

        if (resetPassword==null){
            response.put("success", false);
            return response;
        }else {
            response.put("success", true);
            return response;
        }
    }

    @PostMapping("/reset")
    public Map<String, Boolean> handlePasswordReset(@RequestBody Members members, HttpServletRequest request) {
        Map<String, Boolean> response = new HashMap<>();
        String email = members.getMemberEmail();
        // 檢查此信箱是否有被註冊
        Members byEmail = memberService.findByEmail(email);
        if (byEmail==null){
            response.put("success", false);
            return response;
        }

        // 生成token並存儲在資料庫
        String token = generateToken();
        storeToken(byEmail, token);

        // 建立連結
        String serverName = request.getServerName();
        String servletPath = request.getServletPath();
        int serverPort = request.getServerPort();
        System.out.println(servletPath);

        // 建立重設連結並寄送郵件
        String resetLink = "http://"+serverName+":"+serverPort+"/resetpassword?token=" + token;
        emailService.sendEmail(email, "Password Reset Link", resetLink);

        response.put("success", true);
        return response;
    }

    // 生成token的方法
    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    // 將token存儲到資料庫的方法
    private void storeToken(Members members, String token) {
        members.setToken(token);
        memberService.saveToken(members);
    }

    @GetMapping("/member/memberData/{id}")
    public MembersDTO getMemberById(@PathVariable Integer id){
        return  memberService.getMemberById(id);
    }



    @PostMapping("/checkEmail")
    public Map<String, Boolean> checkEmail(@RequestParam String email){
        Boolean isExist  = memberService.findByEmail(email) != null;
        Map<String, Boolean> response = new HashMap<>();
        response.put("isExist", isExist);
        System.out.println(response);
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

    @PostMapping("/member/update")
    public Map<String, Boolean> updateMember(@RequestBody MembersRegisterDTO dto, HttpSession session) {
        Members members = memberService.updateMember(dto);
        session.setAttribute("user", members);
        Boolean success = members != null;
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", success);
        return response;
    }

    // 後臺修改會員狀態以及分類
    @PostMapping("/member/updateMemberCategory")
    public Map<String, Boolean> updateMemberCategory(@RequestBody Members members) {
        Members membersInDB = memberService.updateMemberCategory(members);
        Boolean success = membersInDB != null;
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", success);
        return response;
    }
}
