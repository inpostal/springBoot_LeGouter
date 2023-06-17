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
public class GroupProductDTO {

	//private Integer groupProductId;
	private String groupProductName;
	private String groupProductContent;
	private Integer groupProductPrice;
	private String groupProductStardate;
	//private String groupProductEnddate;
	private Integer groupProductStatus;
}
