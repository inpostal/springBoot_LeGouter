package app.com.dessertOrderDetail.entity;

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
public class OrderDetailId implements Serializable {
    private Integer orderId;
    private Integer dessertId;

}

