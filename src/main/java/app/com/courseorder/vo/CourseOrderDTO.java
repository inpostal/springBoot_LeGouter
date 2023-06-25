package app.com.courseorder.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class CourseOrderDTO {
    private Integer courseOrderId;
    private String memberName;
    private Integer courseOrderPrice;
    private Date courseDate;
    private String courseName;
}
