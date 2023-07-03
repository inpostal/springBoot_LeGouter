package app.com.CourseStudentComment.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
@Data
public class CourseCommentDTO {

    private String courseName;
    private Integer courseId;
    private Integer memId;
    private Integer courseStudentCommentId;
    private String courseStudentCommentContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date courseStudentCommentDate;
    private String empName;
    private Integer empId;
    private String chefCommentContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date chefCommentDate;



}
