package app.com.dessertCart.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ClassName: DessertCart
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/27 PM 04:56
 */
@Data
@Entity
@IdClass(DessertCartId.class)
@Table(name = "cart_detail")
public class DessertCart implements Serializable {
    private static final long serialVersionUID = 991064508818802335L;

    @Id
    @Column(name = "DESSERT_ID")
    private Integer dessertId;

    @Id
    @Column(name = "MEM_ID")
    private Integer memId;

    @Column(name = "CART_DESSERT_AMOUNT")
    private Integer cartDessertAmount;
}
