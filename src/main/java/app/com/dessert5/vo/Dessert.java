package app.com.dessert5.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dessert")
public class Dessert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DESSERT_ID")
    private Integer dessertId;

    @Column(name = "DESSERT_NAME", unique = true, nullable = false)
    private String dessertName;

    @Column(name = "DESSERT_PRICE", nullable = false)
    private Integer dessertPrice;

    @Column(name = "DESSERT_TYPE_ID")
    private Integer dessertTypeId;

    @Column(name = "DESSERT_TIME")
    private Date dessertTime;

    @Column(name = "DESSERT_CONTENT", nullable = false)
    private String dessertContent;

    @Column(name = "DESSERT_STATUS", nullable = false)
    private String dessertStatus;

    @Column(name = "RATE_NUM")
    private Integer rateNum;

    @Column(name = "RATE_STAR")
    private Integer rateStar;

    @JsonManagedReference
    @OneToMany(mappedBy = "dessert", cascade = CascadeType.ALL)
        private List<DessertImage> dessertImageList;


//    public List<DessertImage> getDessertImageList() {
//        return dessertImageList;
//    }
//
//    public void setDessertImageList(List<DessertImage> dessertImageList) {
//        this.dessertImageList = dessertImageList;
//    }
}
