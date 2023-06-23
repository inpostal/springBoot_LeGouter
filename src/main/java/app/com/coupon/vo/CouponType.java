package app.com.coupon.vo;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "coupon_type")
public class CouponType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CP_ID")
    private Integer cpId;

    @Column(name = "CP_THRESHOLD")
    private Integer cpThreshold;

    @Column(name = "CP_TP")
    private Integer cpTp;

    @Column(name = "CP_NAME")
    private String cpName;

    @Column(name = "CP_DISCOUNT")
    private Integer cpDiscount;

    @Column(name = "CP_START")
    private LocalDateTime cpStart;

    @Column(name = "CP_END")
    private LocalDateTime cpEnd;

    @Column(name = "CP_STATUS")
    private Integer cpStatus;

    @Lob
    @Column(name = "CP_PIC")
    private byte[] cpPic;
}