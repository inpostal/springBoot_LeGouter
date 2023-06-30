package app.com.dessertCart.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * ClassName: DessertCart
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/27 PM 04:56
 */

@Entity
@Data
@Table(name = "DESSERT_CART")
@IdClass(DessertCartId.class)
public class DessertCart {
    @Id
    @Column(name = "DESSERT_ID")
    private Integer dessertId;

    @Id
    @Column(name = "MEM_ID")
    private Integer memberId;

    @Column(name = "CART_DESSERT_QUANTITY", nullable = false)
    private Integer cartDessertQuantity;

    // Getters and setters
}