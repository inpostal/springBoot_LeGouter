package app.com.dessertOrderDetail.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * (OrderDetail)
 *
 * @author makejava
 * @since 2023-06-23 17:31:06
 */
@Data
@Entity
@IdClass(OrderDetailId.class)
@Table(name = "order_detail")
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 856717034077715874L;

    @Id
    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Id
    @Column(name = "DESSERT_ID")
    private Integer dessertId;

    @Column(name = "DESSERT_AMOUNT")
    private Integer dessertAmount;

    @Column(name = "DESSERT_PRICE")
    private Integer dessertPrice;

    @Column(name = "DESSERT_REVIEW_DATE")
    private Date dessertReviewDate;

    @Column(name = "DESSERT_RATE_STAR")
    private Integer dessertRateStar;


}


