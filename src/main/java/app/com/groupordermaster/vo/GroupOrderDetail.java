//package app.com.groupordermaster.vo;
//
//import javax.persistence.*;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.io.Serializable;
//
//@Data
//@Entity
//@Table(name = "group_order_detail")
//@IdClass(GroupOrderId.class)
//public class GroupOrderDetail {
//
//    @Id
//    @Column(name = "GROUP_ORDER_ID")
//    private Integer groupOrderId;
//
//    @Id
//    @Column(name = "MEM_ID")
//    private Integer memId;
//
//    @Column(name = "GROUP_ORDER_AMOUNT")
//    private Integer groupOrderAmount;
//
//    @Column(name = "ADDRESS", length = 100)
//    private String address;
//
//    @Column(name = "GROUP_PRODUCT_PAYING")
//    private byte groupProductPaying;
//
//    @Column(name = "GROUP_PRODUCT_STATUS")
//    private byte groupProductStatus;
//
//    @Column(name = "GROUP_PRODUCT_OTHERS", length = 150)
//    private String groupProductOthers;
//
//    @Column(name = "RECEIVER_NAME", length = 50)
//    private String receiverName;
//
//    @Column(name = "RECEIVER_ADDRESS", length = 100)
//    private String receiverAddress;
//
//    @Column(name = "RECEIVER_PHONE", length = 10)
//    private String receiverPhone;
//
//    @Column(name = "GROUP_PRODUCT_PRICE")
//    private double groupProductPrice;
//
//    @Data
//    @NoArgsConstructor //需要⼀個空建構⼦
//    public class GroupOrderId implements Serializable {
//        private Integer  groupOrderId;
//        private Integer memId;
//    }
//
//}