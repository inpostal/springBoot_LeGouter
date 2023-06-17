package app.com.group.vo;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "group_product",catalog = "legouter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupProductVO{

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "GROUP_PRODUCT_ID")
	private Integer groupProductId;
	@Column (name = "GROUP_PRODUCT_NAME")
	private String groupProductName;
	@Column (name = "GROUP_PRODUCT_CONTENT")
	private String groupProductContent;
	@Column (name = "GROUP_PRODUCT_PRICE")
	private Integer groupProductPrice;
	@Column (name = "GROUP_PRODUCT_STARDATE")
	private Date groupProductStardate;
	@Column (name = "GROUP_PRODUCT_ENDDATE ")
	private Date groupProductEnddate;
	@Column (name = "GROUP_PRODUCT_STATUS")
	private Integer groupProductStatus;
	
	
}
