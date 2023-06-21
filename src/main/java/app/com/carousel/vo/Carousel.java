package app.com.carousel.vo;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "carousel_picture")
public class Carousel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAR_PIC_ID")
    private int carPicId;

    @Column(name = "CAR_TAG")
    private String carTag;

    @Column(name = "CAR_TITLE")
    private String carTitle;

    @Column(name = "CAR_PIC_CONTENT")
    private String carPicContent;

    @Column(name = "CAR_PIC_LINK_TITLE")
    private String carPicLinkTitle;

    @Column(name = "CAR_PIC_LINK")
    private String carPicLink;

    @Lob
    @Column(name = "CAR_PIC_PIC")
    private byte[] carPicPic;
}

