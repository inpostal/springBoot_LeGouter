package app.com.grouporderdetail.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class GroupOrderDetailId implements Serializable {
    private Integer groupOrderId;
    private Integer memberId;
}
