package app.com.dessert5.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Dessert5CartPK implements Serializable {

    @Column(name = "DESSERT_ID")
    private Integer dessertId;

    @Column(name = "MEM_ID")
    private Integer memberId;
}
