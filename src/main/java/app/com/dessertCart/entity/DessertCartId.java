package app.com.dessertCart.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: OrderDetailId
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/24 AM 12:07
 */
@Data
@NoArgsConstructor
public class DessertCartId implements Serializable {
    private Integer dessertId;
    private Integer memId;

}

