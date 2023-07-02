package app.com.dessert5.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dessert_cart")
public class Dessert5Cart implements Serializable {

    @EmbeddedId
    private Dessert5CartPK id;

    @Column(name = "CART_DESSERT_QUANTITY", nullable = false)
    private Integer quantity;
}
