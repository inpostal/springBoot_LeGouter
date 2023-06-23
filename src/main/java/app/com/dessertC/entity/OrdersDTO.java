package app.com.dessertC.entity;

import lombok.Data;

import java.text.SimpleDateFormat;
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

    public String getOrdersDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(ordersDate);
    }

    public void setOrdersDate(Date ordersDate) {
        this.ordersDate = ordersDate;
    }

    private Integer cpOrderTotal;

    private Integer orderStatus;

}
