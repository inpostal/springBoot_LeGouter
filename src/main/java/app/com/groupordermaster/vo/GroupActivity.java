package app.com.groupordermaster.vo;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "group_activity")
public class GroupActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_ACTIVITY_ID")
    private Integer groupActivityId;

    @Column(name = "GROUP_PRODUCT_ID")
    private Integer groupProductId;

    @Column(name = "GROUP_ACTIVITY_CONTENT", length = 300)
    private String groupActivityContent;

    @Column(name = "GROUP_ORDER_STAR")
    private Date groupOrderStar;

    @Column(name = "GROUP_ORDER_END")
    private Date groupOrderEnd;

    @Column(name = "GROUP_ORDER_MIN")
    private Integer groupOrderMin;

    @Column(name = "GROUP_NAME", length = 40)
    private String groupName;

    @Column(name = "GROUP_ORDER_DISCOUNT")
    private Double groupOrderDiscount;
}