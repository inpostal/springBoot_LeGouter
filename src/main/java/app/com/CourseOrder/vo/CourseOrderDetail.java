package app.com.CourseOrder.vo;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "course_order_detail")
public class CourseOrderDetail {

    @Id
    @Column(name = "COURSE_ORDER_ID")
    private Integer courseOrderId;

    @Id
    @Column(name = "COURSE_ID")
    private Integer courseId;

    @Column(name = "COURSE_PRICE")
    private Integer coursePrice;

    @Column(name = "COURSE_REVIEW_DATE")
    private Timestamp courseReviewDate;

    @Column(name = "COURSE_RATE_STAR")
    private Integer courseRateStar;
}


