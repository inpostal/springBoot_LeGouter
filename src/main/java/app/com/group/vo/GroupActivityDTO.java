package app.com.group.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date groupOrderStar; //開始日期
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date groupOrderEnd; //結束日期
	private Integer groupOrderMin; //達標數
	private String groupName; //活動名稱
	private Double groupOrderDiscount; //折扣數
	
	private Long groupDetailCount; //依照訂單明細的資料筆數 統計參加人數
	
	private Integer groupProductPrice; //團購商品原價
	private Integer groupActivityPrice; //活動打折價格
	private String groupProductName; //團購商品名稱
	private String groupProductContent; //團購商品內容

	private String memberName; //團購主名稱
	private Integer numberOfProduct; //團購訂單主檔的總數量

}
