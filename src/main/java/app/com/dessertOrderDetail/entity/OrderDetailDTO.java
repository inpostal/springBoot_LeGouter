package app.com.dessertOrderDetail.entity;

import lombok.Data;

/**
 * ClassName: OrderDetailDTO
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/24 PM 04:39
 */
@Data
public class OrderDetailDTO {
    private Integer orderId;
    private Integer dessertId;
    private Integer dessertPrice;
    private Integer dessertAmount;
    private String dessertName;
    private byte[] dessertImg;

}
