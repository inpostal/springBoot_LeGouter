package app.com.groupordermaster.vo;

import lombok.Data;

import java.sql.Date;
@Data
public class GroupOrderMemDTO {
    private Integer memberID;
    private Integer groupOrderId;
    private Integer ActivityId;
    private Integer groupOrderStatus;

    private Integer groupProductId;
    private String groupActivityContent;
    private String groupName;
    private Double groupOrderDiscount;
    private Integer groupOrderMin;
private String groupProductName;
    private Date groupOrderStar;
    private Date groupOrderEnd;

//    private byte[] groupProductImg;
    private Integer groupProductPrice;


}
