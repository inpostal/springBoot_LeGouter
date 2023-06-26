package app.com.course.vo;

import lombok.Data;

@Data
public class CheckoutDTO {

    private Integer memberId;

    private String memberName;

    private String memberEmail;

    private String memberPhone;

    private Integer courseId;

    private String courseName;

    private Integer coursePrice;
}
