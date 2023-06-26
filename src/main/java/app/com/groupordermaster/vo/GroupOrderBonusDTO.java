package app.com.groupordermaster.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class GroupOrderBonusDTO {
    private Integer memberID;
    private Integer groupOrderId;
    private Date groupOrderStar;
    private Date groupOrderEnd;
    private Integer ActivityId;
    private Integer totalGroupProductPrice;
//    private Integer groupOrderMin;
//    private  Integer bonusRate;
    private  String bonusRate;
    private Integer groupOrderBonus;
    private String groupOrderBonusStatus;
}
