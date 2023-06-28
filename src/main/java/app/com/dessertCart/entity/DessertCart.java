package app.com.dessertCart.entity;

import app.com.dessert5.vo.Dessert;
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
public class DessertCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DESSERTCART_ID")
    private Integer dessertCartId;

    @Column(name = "DESSERT_ID")
    private Integer dessertId;

    @Column(name = "MEM_ID")
    private Integer memberId;

    @Column(name = "CART_DESSERT_QUANTITY", nullable = false)
    private Integer cartDessertQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DESSERT_ID", referencedColumnName = "DESSERT_ID", insertable = false, updatable = false)
    private Dessert dessert;


}