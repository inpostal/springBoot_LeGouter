package app.com.group.vo;


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
@Table(name = "group_product_img",catalog = "legouter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupProductImgVO {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "GROUP_PRODUCT_IMG_ID")
	private Integer groupProductImgId;
	@Column (name = "GROUP_PRODUCT_ID")
	private Integer groupProductId;
	@Column (name = "GOURP_PRODUCT_IMG")
	private byte[] groupProductImg;
}
