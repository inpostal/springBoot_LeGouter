package app.com.grouporderdetail.vo;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "group_order_detail")
@IdClass(GroupOrderDetailId.class)
public class GroupOrderDetail {
    @Id
    @Column(name = "GROUP_ORDER_ID")
    private Integer groupOrderId;

    @Id
    @Column(name = "MEM_ID")
    private Integer memberId;

    @Column(name = "GROUP_ORDER_AMOUNT")
    private Integer groupOrderAmount;

    @Column(name = "GROUP_PRODUCT_PAYING")
    private byte groupProductPaying;

    @Column(name = "GROUP_PRODUCT_STATUS")
    private byte groupProductStatus;

    @Column(name = "GROUP_PRODUCT_OTHERS")
    private String groupProductOthers;

    @Column(name = "RECEIVER_NAME")
    private String receiverName;

    @Column(name = "RECEIVER_ADDRESS")
    private String receiverAddress;

    @Column(name = "RECEIVER_EMAIL")
    private String receiverEmail;

    @Column(name = "RECEIVER_PHONE")
    private String receiverPhone;

    @Column(name = "GROUP_PRODUCT_PRICE")
    private Integer groupProductPrice;



}

