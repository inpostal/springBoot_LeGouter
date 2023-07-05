package app.com.coupon.vo;

import lombok.Data;

@Data
public class CheckOutDto {
    private Integer cpId;
    private Integer discount;
    private String cpName;
}
