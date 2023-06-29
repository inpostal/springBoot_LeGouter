package app.com.group.vo;

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
public class InserDetailDTO {
	
	private Integer groupOrderId; //FK訂單主檔編號
	private Integer memberId; //FK一般會員編號
//	待補上信箱
	private Integer groupOrderAmount; //訂購數量
	private byte groupProductPaying; //付款方式
	private byte groupProductStatus; //詳細訂單之狀態
	private String groupProductOthers; //備註
	private String receiverName; //收件人姓名
	private String receiverAddress; //收件人地址
	private String receiverEmail; //收件人電子信箱
	private String receiverPhone; //收件人電話
	
	private Integer groupActivityPrice; //活動打折價格

}
