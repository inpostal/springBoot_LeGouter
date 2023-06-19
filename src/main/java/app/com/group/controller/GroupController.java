package app.com.group.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupController {

	@GetMapping("/group-product-manage") //後台團購商品管理
	public String groupProductManage() {
		return "back-end/group/group-product-manage";
	}
    @GetMapping("/plan-activity-manage") //後台團購活動管理
	public String planActivityManage() {
		return "back-end/group/plan-activity-manage";
	}
    @GetMapping("/group-shop") //前台團購活動瀏覽平台
	public String groupShop() {
		return "front-end/group/group-shop";
	}
    @GetMapping("/plan-activity") //前台團購主活動瀏覽專區
	public String planActivity() {
		return "front-end/group/plan-activity";
	}
    @GetMapping("/single-product") //前台團購活動單品頁
	public String singleProduct() {
		return "front-end/group/single-product";
	}
}
