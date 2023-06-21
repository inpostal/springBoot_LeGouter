package app.com.dessertC.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * (Orders)实体类
 *
 * @author makejava
 * @since 2023-06-21 10:35:13
 */
@Data
@Entity
@Table(name = "orders")
public class Orders implements Serializable {
    private static final long serialVersionUID = 187828948400588724L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Integer orderId;
    
    //    @ManyToOne
//    @JoinColumn(name = "MEM_ID")
//    private Member member;
//
//    @ManyToOne
//    @JoinColumn(name = "CP_ID")
//    private Coupon coupon;
    @Column(name = "MEM_ID")
    private Integer memId;

    @Column(name = "CP_ID", insertable = false)
    private Integer cpId;

    @Column(name = "ORDER_TOTAL", insertable = false)
    private Integer orderTotal;

    @Column(name = "CP_ORDER_TOTAL", insertable = false)
    private Integer cpOrderTotal;

    @Column(name = "ORDER_STATUS")
    private Integer orderStatus;

    @Column(name = "ORDER_TIME", insertable = false)
    private Date orderTime;

    @Column(name = "RECEIVER_PHONE")
    private String receiverPhone;

    @Column(name = "RECEIVER_ADDRESS")
    private String receiverAddress;

    @Column(name = "RECEIVER_NAME")
    private String receiverName;


}

