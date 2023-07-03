package app.com.dessertCart.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: DessertCartId
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/30 PM 04:15
 */
@Data
public class DessertCartId implements Serializable {
    private Integer dessertId;
    private Integer memberId;
}