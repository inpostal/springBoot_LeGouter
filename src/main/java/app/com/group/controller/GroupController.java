package app.com.group.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupController {

	@GetMapping("/group-product-manage") //24自己加的
	public String groupProductManage() {
		return "back-end/group/group-product-manage";
	}
    @GetMapping("/plan-activity-manage") //24自己加的
	public String planActivityManage() {
		return "back-end/group/plan-activity-manage";
	}
    @GetMapping("/group-shop") //24自己加的
	public String groupShop() {
		return "front-end/group/group-shop";
	}
    @GetMapping("/plan-activity") //24自己加的
	public String planActivity() {
		return "front-end/group/plan-activity";
	}
    @GetMapping("/single-product") //24自己加的
	public String singleProduct() {
		return "front-end/group/single-product";
	}
}
