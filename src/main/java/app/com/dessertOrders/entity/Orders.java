package app.com.dessertOrders.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * (Orders)實體類
 *
 * @author Charlie
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


    @Column(name = "MEM_ID")
    private Integer memId;

    @Column(name = "CP_ID", insertable = false)
    private Integer cpId;

    @Column(name = "ORDER_TOTAL")
    private Integer orderTotal;

    @Column(name = "CP_ORDER_TOTAL")
    private Integer cpOrderTotal;

    @Column(name = "ORDER_STATUS")
    private Integer orderStatus = 0;

    @Column(name = "ORDER_TIME")
    private Timestamp orderTime;

    @Column(name = "RECEIVER_PHONE")
    private String receiverPhone;

    @Column(name = "RECEIVER_ADDRESS")
    private String receiverAddress;

    @Column(name = "RECEIVER_NAME")
    private String receiverName;

    @Column(name = "RECEIVER_EMAIL")
    private String receiverEmail;
}

