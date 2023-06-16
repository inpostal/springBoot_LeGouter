package app.com.member.vo;

import lombok.*;

import java.sql.Date;
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MembersDTO {
    private Integer memberId;
    private Integer memberClassify;
    private String memberName;
    private String memberAccount;
    private String memberPassword;
    private Character memberGender;
    private String memberPhone;
    private String memberEmail;
    private String memberAddress;
    private Date memberBirthday;
    private Integer memberStatus;
    private Date memberRegistrationTime;
}
