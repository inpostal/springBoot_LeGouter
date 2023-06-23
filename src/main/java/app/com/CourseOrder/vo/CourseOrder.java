package app.com.CourseOrder.vo;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;

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

    @Column(name = "MEM_ID")
    private Integer memId;

    @Column(name = "ORDER_TOTAL")
    private Integer orderTotal;

    @Column(name = "CP_ORDER_TOTAL")
    private Integer cpOrderTotal;

    @Column(name = "ORDER_STATUS")
    private Integer orderStatus;

    @Column(name = "ORDER_TIME")
    private Timestamp orderTime;
}
