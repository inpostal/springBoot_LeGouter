package app.com.group.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.com.group.service.GroupActivityService;
import app.com.group.service.GroupProductService;
import app.com.group.vo.GroupActivityVO;
import app.com.group.vo.GroupProductVO;

@Controller
public class GroupController {
	
	@Autowired
	private GroupProductService groupProductService;
	
	@Autowired
	private GroupActivityService groupActivityService;
	

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
		return "front-end/group/plan-activity2";
	}
    @GetMapping("/single-product") //前台團購活動單品頁
	public String singleProduct(@RequestParam Integer groupActivityId, Model model) {
    	GroupActivityVO groupActivityVO = groupActivityService.showTheActivity(groupActivityId);
    	 model.addAttribute("groupActivityVO", groupActivityVO);
		return "front-end/group/single-product2";
	}
    @GetMapping("/group-product/updata") //後台團購商品管理-修改資料用頁面
    public String groupProductUpdata(@RequestParam Integer groupProductId, Model model) {
    	//拿到id裡面資料
    	GroupProductVO groupProductVO = groupProductService.showOneProduct(groupProductId);
        //將id裡面資料放入model
        model.addAttribute("groupProductVO", groupProductVO);
        //導至頁面
    	return "back-end/group/group-product-updata";
    }
//    @GetMapping("/plan-activity/updata") //前台團購主修改活動資料
//	public String theActivityUp(@RequestParam Integer groupActivityId, Model model) {
//    	GroupActivityVO groupActivityVO = groupActivityService.showTheActivity(groupActivityId);
//    	 model.addAttribute("groupActivityVO", groupActivityVO);
//		return "front-end/group/";
//    }
}
