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
@Table(name = "group_activity",catalog = "legouter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupActivityVO {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "GROUP_ACTIVITY_ID")
	private Integer groupActivityId; //活動編號
	@Column (name = "GROUP_PRODUCT_ID")
	private Integer groupProductId; //FK團購商品編號
	@Column (name = "GROUP_ACTIVITY_CONTENT")
	private String groupActivityContent; //內容
	@Column (name = "GROUP_ORDER_STAR")
	private String groupOrderStar; //開始日期
	@Column (name = "GROUP_ORDER_END")
	private String groupOrderEnd; //結束日期
	@Column (name = "GROUP_ORDER_MIN")
	private Integer groupOrderMin; //達標數
	@Column (name = "GROUP_NAME")
	private String groupName; //活動名稱
	@Column (name = "GROUP_ORDER_DISCOUNT")
	private double groupOrderDiscount; //折扣數

}
