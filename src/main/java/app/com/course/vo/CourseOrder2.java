package app.com.course.vo;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "course_order")
public class CourseOrder2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ORDER_ID")
    private Integer courseOrderId;

    @Column(name = "CP_ID")
    private Integer cpId;

    @Column(name = "MEM_ID")
    private Integer memId;

    @Column(name = "ORDER_TOTAL")
    private Integer orderTotal;

    @Column(name = "CP_ORDER_TOTAL")
    private Integer cpOrderTotal;

    @Column(name = "ORDER_TIME", insertable = false)
    private Date orderTime;

    @Column(name = "COURSE_ID")
    private Integer courseId;


}
