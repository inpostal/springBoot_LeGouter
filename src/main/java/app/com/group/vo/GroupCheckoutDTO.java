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
public class GroupCheckoutDTO {
	
	private Integer groupOrderId; //訂單編號
	private Integer memberId; //會員編號
	
	private Integer groupActivityId; //活動編號
	
	private Integer groupActivityPrice; //活動打折價格
	
	private String groupName; //活動名稱

}
