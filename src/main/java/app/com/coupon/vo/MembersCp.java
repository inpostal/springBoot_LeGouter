package app.com.coupon.vo;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "members_cp")
@IdClass(MembersCpId.class)
public class MembersCp {

    @Id
    @Column(name = "CP_ID")
    private int cpId;

    @Id
    @Column(name = "MEM_ID")
    private int memId;

    @Column(name = "CP_USED")
    private byte cpUsed;
}

