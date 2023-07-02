package app.com.dessertWishlist.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@IdClass(DessertWishlistId.class)
@Table(name = "love_dessert")
public class DessertWishlist implements java.io.Serializable {

    @Id
    @Column(name = "dessert_id")
    private Integer dessertId;

    @Id
    @Column(name = "mem_id")
    private Integer memId;
}
