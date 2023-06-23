package app.com.CourseOrder.vo;

import lombok.Data;

import javax.persistence.IdClass;
import java.io.Serializable;

@IdClass(CourseOrderDetailId.class)
@Data
public class CourseOrderDetailId implements Serializable {
    private Integer courseOrderId;
    private Integer courseId;
}
