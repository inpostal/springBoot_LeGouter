package app.test.vo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBERS")
@Entity
public class Members{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEM_ID")
    private Integer memberId;
    @Column(name = "MEM_CLASSIFY")
    private Integer memberClassify;
    @Column(name = "MEM_NAME")
    private String memberName;
    @Column(name = "MEM_ACCOUNT")
    private String memberAccount;
    @Column(name = "MEM_PASSWORD")
    private String memberPassword;
    @Column(name = "MEM_GENDER")
    private Character memberGender;
    @Column(name = "MEM_PHONE")
    private String memberPhone;
    @Column(name = "MEM_EMAIL")
    private String memberEmail;
    @Column(name = "MEM_ADDRES")
    private String memberAddress;
    @Column(name = "MEM_BIRTHDAY")
    private Date memberBirthday;
    @Column(name = "MEM_STATUS")
    private Integer memberStatus;
    @Column(name = "MEM_REGIS_TIME")
    private Date memberRegistrationTime;
}
