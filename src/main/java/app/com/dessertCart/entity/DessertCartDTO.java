package app.com.dessertCart.entity;

import lombok.Data;

/**
 * ClassName: DessertCartDTO
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/29 AM 01:16
 */
@Data
public class DessertCartDTO {
    private Integer dessertCartId;
    private Integer dessertId;
    private Integer memberId;
    private Integer cartDessertQuantity;
    private Integer subtotalAmount;
    private String dessertName;
    private Integer dessertPrice;

}
