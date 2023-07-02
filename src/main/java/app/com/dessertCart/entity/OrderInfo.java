package app.com.dessertCart.entity;

import lombok.Data;

/**
 * ClassName: OrderInfo
 * Description:
 *
 * @Author Charlie
 * @Create 2023/7/2 PM 11:20
 */
@Data
public class OrderInfo {
    private String receiverName;
    private String receiverAddress;
    private String receiverEmail;
    private String receiverPhone;
    private Integer shippingCost;
}
