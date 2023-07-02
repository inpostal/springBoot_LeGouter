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
@IdClass(Dessert5LoveList.class)
@Table(name = "love_dessert")
public class Dessert5LoveList implements Serializable {

    @Id
    @Column(name = "DESSERT_ID")
    private Integer dessertId;

    @Id
    @Column(name = "MEM_ID")
    private Integer memberId;
}
