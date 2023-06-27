package app.com.dessertOrders.entity;

import lombok.Data;

import java.sql.Timestamp;


/**
 * ClassName: OrdersDTO
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/23 AM 10:46
 */
@Data
public class OrdersDTO {
    private Integer orderId;
    private String memberAC;
    private Timestamp ordersDate;
    private Integer cpOrderTotal;
    private Integer orderStatus;

}
