package app.com.CourseStudentComment.vo;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "student_comment")
public class CourseStudentComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_COMMENT_ID")
    private Integer studentCommentId;

    @Column(name = "COURSE_ID")
    private Integer courseId;

    @Column(name = "MEM_ID")
    private Integer memId;

    @Column(name = "STUDENT_COMMENT_CONTENT")
    private String studentCommentContent;

    @Column(name = "STUDENT_COMMENT_DATE",insertable = false)
    private Date studentCommentDate;

    @Column(name = "EMP_ID")
    private Integer empId;

    @Column(name = "CHEF_COMMENT_CONTNENT")
    private String chefCommentContent;

    @Column(name = "CHEF_COMMENT_DATE",insertable = false)
    private Date chefCommentDate;
}