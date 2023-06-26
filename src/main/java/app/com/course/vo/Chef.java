package app.com.course.vo;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "chef")
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHEF_ID")
    public int chefId;

    @Column(name = "EMP_ID")
    public int empId;

    @Column(name = "CHEF_INFO")
    public String chefInfo;

}
