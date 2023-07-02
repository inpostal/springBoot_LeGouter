package app.com.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "love_course")
@IdClass(LoveCourse.class)
public class LoveCourse implements Serializable {
    @Id
    @Column(name = "COURSE_ID", nullable = false)
    private Integer courseId;

    @Id
    @Column(name = "MEM_ID", nullable = false)
    private Integer memId;
}
