package app.com.dessert5.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DESSERT_IMG")
public class DessertImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DESSERT_IMG_ID")
    private Integer dessertImageId;

    @Lob
    @Column(name = "DESSERT_IMG")
    private byte[] dessertImage;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "DESSERT_ID", referencedColumnName = "DESSERT_ID")
    private Dessert dessert;
}