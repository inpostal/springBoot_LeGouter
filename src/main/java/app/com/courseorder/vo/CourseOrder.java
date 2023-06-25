package app.com.courseorder.vo;

import lombok.Data;
import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "course_order")
public class CourseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ORDER_ID")
    private Integer courseOrderId;

    @Column(name = "CP_ID")
    private Integer cpId;

    @Column(name = "COURSE_ID")
    private Integer courseId;

    @Column(name = "MEM_ID", nullable = false)
    private Integer memId;

    @Column(name = "ORDER_TOTAL", nullable = false)
    private Integer orderTotal;

    @Column(name = "CP_ORDER_TOTAL", nullable = false)
    private Integer cpOrderTotal;

    @Column(name = "ORDER_TIME", insertable = false)
    private Date orderTime;
}

