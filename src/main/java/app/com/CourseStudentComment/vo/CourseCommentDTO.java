package app.com.CourseStudentComment.vo;

import lombok.Data;

import java.sql.Date;
@Data
public class CourseCommentDTO {

    private String courseName;
    private Integer courseId;
    private Integer memId;
    private Integer courseStudentCommentId;
    private String courseStudentCommentContent;
    private Date courseStudentCommentDate;
    private String empName;
    private Integer empId;
    private String chefCommentContent;
    private Date chefCommentDate;



}
