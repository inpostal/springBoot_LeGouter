package app.com.emp.vo;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "emp")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_ID")
    private Integer empId;

    @Column(name = "EMP_NAME")
    private String empName;

    @Column(name = "EMP_PHONE")
    private String empPhone;

    @Column(name = "EMP_PICTURE")
    private byte[] empPicture;

    @Column(name = "EMP_ACCOUNT")
    private String empAccount;

    @Column(name = "EMP_MAIL")
    private String empMail;

    @Column(name = "EMP_PASSWORD")
    private String empPassword;

    @Column(name = "EMP_CLASSIFY")
    private Integer empClassify;

    @Column(name = "EMP_STATUS")
    private Integer empStatus;

    @Column(name = "EMP_HIREDATE")
    private Date empHireDate;
}

