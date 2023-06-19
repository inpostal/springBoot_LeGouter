package app.com.course.vo;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ID")
    private Integer courseId;

    @Column(name = "COURSE_NAME")
    private String courseName;

    @Column(name = "COURSE_PRICE")
    private Integer coursePrice;

    @Column(name = "EMP_ID", insertable = true)
    private Integer empId=1;

    @Column(name = "COURSE_TIME", insertable = false)
    private Timestamp courseTime;

    @Column(name = "COURSE_CONTENT")
    private String courseContent;

    @Column(name = "COURSE_STATUS")
    private Integer courseStatus=0;

    @Column(name = "COURSE_VIDEO")
    private byte[] courseVideo;

    @Column(insertable = false, name = "RATE_NUM")
    private Integer rateNum;

    @Column(insertable = false, name = "RATE_STAR")
    private Integer rateStar;
}

