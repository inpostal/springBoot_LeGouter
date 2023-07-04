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
public class GroupProductImgDTO {
	
	private Integer groupProductImgId;
	private Integer groupProductId;

}
