package app.com.dessertOrdersMem.entity;

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
public class OrdersMem implements Serializable {
    private static final long serialVersionUID = 187828948400588724L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column(name = "MEM_ID")
    private Integer memId;

    @Column(name = "CP_ID", insertable = false)
    private Integer cpId;

    @Column(name = "ORDER_TOTAL", insertable = false)
    private Integer orderTotal;

    @Column(name = "CP_ORDER_TOTAL", insertable = false)
    private Integer cpOrderTotal;

    @Column(name = "ORDER_STATUS")
    private Integer orderStatus = 0;

    @Column(name = "ORDER_TIME", insertable = false)
    private Timestamp orderTime;

    @Column(name = "RECEIVER_PHONE")
    private String receiverPhone;

    @Column(name = "RECEIVER_ADDRESS")
    private String receiverAddress;

    @Column(name = "RECEIVER_NAME")
    private String receiverName;

    @Column(name = "RECEIVER_EMAIL")
    private String receiverEmail;

    public String getStatusClass() {
        switch (getOrderStatus()) {
            case 0:
                return "badge bg-primary";
            case 1:
                return "badge bg-dark";
            case 2:
                return "badge bg-danger";
            default:
                return "badge bg-success";
        }
    }

    public String getStatusName() {
        switch (getOrderStatus()) {
            case 0:
                return "訂單處理中";
            case 1:
                return "運送中";
            case 2:
                return "取消訂單";
            default:
                return "訂單完成";
        }
    }
}

