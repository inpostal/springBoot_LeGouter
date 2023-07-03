package app.com.member.service;

import app.com.member.repository.MemberRepository;
import app.com.member.vo.Members;
import app.com.member.vo.MembersRegisterDTO;
import app.com.member.vo.MembersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    public Members findByEmail(String email) {
        List<Members> membersList = memberRepository.findAll();
        for (Members m:
            membersList) {
            if (email.equals(m.getMemberEmail())){
                return  m;
            }
        }
        return null;
    }

    public Members findByAccount(String account) {
        List<Members> membersList = memberRepository.findAll();
        for (Members m:
             membersList) {
            if (account.equals(m.getMemberAccount())){
                return m;
            }
        }
        return null;
    }

    public Boolean memberRegister(MembersRegisterDTO dto) {
        Members members = new Members();

        members.setMemberClassify(0);
        members.setMemberName(dto.getName());
        members.setMemberAccount(dto.getAccount());
        members.setMemberPassword(dto.getPassword());
        members.setMemberGender(dto.getGender().charAt(0));
        members.setMemberPhone(dto.getPhone());
        members.setMemberEmail(dto.getEmail());
        members.setMemberBirthday(Date.valueOf(dto.getBirthday()));
        members.setMemberStatus(0);
        members.setMemberAddress(dto.getCity()+","+dto.getDistrict()+","+dto.getAddress());

        return memberRepository.save(members) != null;
    }

    public Members updateMember(MembersRegisterDTO dto) {
        Members members = memberRepository.getReferenceById(dto.getId());

        members.setMemberGender(dto.getGender().charAt(0));
        members.setMemberName(dto.getName());
        members.setMemberPassword(dto.getPassword());
        members.setMemberAddress(dto.getCity()+","+dto.getDistrict()+","+dto.getAddress());
        members.setMemberPhone(dto.getPhone());
        memberRepository.save(members);
        return members;
    }

    public List<Members> getAllMember() {
        return memberRepository.findAll();
    }

    public Members updateMemberCategory(Members members) {
        Members memberInDB = memberRepository.getReferenceById(members.getMemberId());
        memberInDB.setMemberClassify(members.getMemberClassify());
        memberInDB.setMemberStatus(members.getMemberStatus());
        return memberRepository.save(memberInDB);
    }

    public Members saveToken(Members members) {
        return memberRepository.save(members);
    }

    public Members findByToken(String token) {
        return memberRepository.findByToken(token);
    }

    public Members resetPassword(Members members) {
        Members referenceById = memberRepository.getReferenceById(members.getMemberId());
        referenceById.setToken(null);
        referenceById.setMemberPassword(members.getMemberPassword());
        return memberRepository.save(referenceById);
    }
}
