package app.test.service;

import app.test.repository.MemberRepository;
import app.test.vo.Members;
import app.test.vo.MembersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    public MembersDTO getMemberById(Integer id){
        Members members = memberRepository.getReferenceById(id);
        MembersDTO dto = new MembersDTO();
        dto.setMemberId(members.getMemberId());
        dto.setMemberAddress(members.getMemberAddress());
        dto.setMemberAccount(members.getMemberAccount());
        dto.setMemberBirthday(members.getMemberBirthday());
        dto.setMemberEmail(members.getMemberEmail());
        dto.setMemberClassify(members.getMemberClassify());
        dto.setMemberGender(members.getMemberGender());
        dto.setMemberName(members.getMemberName());
        dto.setMemberPhone(members.getMemberPhone());
        dto.setMemberPassword(members.getMemberPassword());
        dto.setMemberStatus(members.getMemberStatus());
        return dto;
    }
    public Members login(String memberAccount, String memberPassword) {
        List<Members> membersList = memberRepository.findAll();
        for (Members m:
             membersList) {
            if (m.getMemberAccount().equals(memberAccount) && m.getMemberPassword().equals(memberPassword)){
                return m;
            }
        }
        return null;
    }
}
