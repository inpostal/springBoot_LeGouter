package app.com.group.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutMemberDTO {
	
	private String memberName; //會員姓名
	
	private String memberAddress; //會員地址
	
	private String memberPhone; //會員電話
	
	private String memberEmail; //會員信箱

}
