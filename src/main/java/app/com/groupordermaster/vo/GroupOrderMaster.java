package app.com.groupordermaster.vo;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "group_order_master")
public class GroupOrderMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_ORDER_ID")
    private Integer groupOrderId;

    @Column(name = "MEM_ID")
    private Integer memId;

    @Column(name = "GROUP_ACTIVITY_ID")
    private Integer groupActivityId;

    @Column(name = "GROUP_ORDER_STATUS")
    private Integer groupOrderStatus;

    @Column(name = "NUMBER_OF_PRODUCT")
    private Integer numberOfProduct;


    @Column(name = "GROUP_ORDER_BONUS")
    private Integer groupOrderBonus;

    @Column(name = "TOTAL_GROUP_PRODUCT_PRICE")
    private Integer totalGroupProductPrice;

    @Column(name = "GROUP_ORDER_BONUS_STATUS")
    private Integer groupOrderBonusStatus;


}