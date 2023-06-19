package app.com.member.vo;

import lombok.Data;

@Data
public class MembersRegisterDTO {
    private Integer id;
    private String name;
    private String account;
    private String password;
    private String gender;
    private String phone;
    private String email;
    private String city;
    private String district;
    private String address;
    private String birthday;
}
