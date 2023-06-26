package app.com.course.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class ChefInfoDTO {

    private String empName;

    private Integer empId;

    private Integer empClassify;

    private String empAccount;

    private String empPhone;

    private byte[] empPicture;

    private Date empHireDate;

    private String empMail;

    private Integer chefId;

    public String chefInfo;


}
