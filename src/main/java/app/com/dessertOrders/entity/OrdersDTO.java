package app.com.dessertOrders.entity;

import lombok.Data;

import java.util.Date;


/**
 * ClassName: OrdersDTO
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/23 AM 10:46
 */
@Data
public class OrdersDTO {
    private Integer orderID;
    private String memberAC;
    private Date ordersDate;
    private Integer cpOrderTotal;
    private Integer orderStatus;
    private Integer orderId;
}
