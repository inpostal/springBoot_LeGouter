package app.com.dessertCart.entity;

import app.com.dessert5.vo.Dessert;
import app.com.member.vo.Members;
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
    private int dessertCartId;

    @ManyToOne
    @JoinColumn(name = "DESSERT_ID")
    private Dessert dessert;

    @ManyToOne
    @JoinColumn(name = "MEM_ID")
    private Members member;

    @Column(name = "CART_DESSERT_QUANTITY", nullable = false)
    private int cartDessertQuantity;

    public double getTotalPrice() {
        if (dessert != null) {
            return dessert.getDessertPrice() * cartDessertQuantity;
        } else {
            return 0.0;
        }
    }

}