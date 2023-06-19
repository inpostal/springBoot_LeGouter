package app.com.group.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupActivityDTO {
	
	private Integer groupActivityId; //活動編號
	private Integer groupProductId; //FK團購商品編號
	private String groupActivityContent; //內容
	private Date groupOrderStar; //開始日期
	private Date groupOrderEnd; //結束日期
	private Integer groupOrderMin; //達標數
	private String groupName; //活動名稱
	private Double groupOrderDiscount; //折扣數

}
