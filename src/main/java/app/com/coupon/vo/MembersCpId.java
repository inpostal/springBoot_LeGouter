package app.com.coupon.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MembersCpId implements Serializable {
    private Integer cpId;
    private Integer memId;
}
