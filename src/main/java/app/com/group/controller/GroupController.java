package app.com.group.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.com.group.service.GroupActivityService;
import app.com.group.service.GroupProductService;
import app.com.group.vo.GroupActivityDTO;
import app.com.group.vo.GroupActivityVO;
import app.com.group.vo.GroupCheckoutDTO;
import app.com.group.vo.GroupProductVO;
import app.com.groupordermaster.vo.GroupOrderMaster;
import app.com.member.vo.Members;

@Controller
public class GroupController {
	
	@Autowired
	private GroupProductService groupProductService;
	
	@Autowired
	private GroupActivityService groupActivityService;
	

	@GetMapping("/group-product-manage") //後台團購商品管理
	public String groupProductManage(HttpSession session) {
		 if (session.getAttribute("emp") != null) {
			 return "back-end/group/group-product-manage2";
	        } else {
	            return "redirect:/employee/login";
	        }
	}
    @GetMapping("/plan-activity-manage") //後台團購活動管理
	public String planActivityManage(HttpSession session) {
    	if (session.getAttribute("emp") != null) {
    		return "back-end/group/plan-activity-manage";
	        } else {
	            return "redirect:/employee/login";
	        }
	}
    @GetMapping("/group-shop") //前台團購活動瀏覽平台
	public String groupShop() {
		return "front-end/group/group-shop";
	}
    @GetMapping("/plan-activity") //前台團購主瀏覽商品 發起活動專區
	public String planActivity(HttpSession session, RedirectAttributes redirectAttributes) {
    	//預設要返回的當前路徑
    	String planactivityurl = "/plan-activity";
    	//取得session當中的會員value
    	Members user = (Members) session.getAttribute("user");
    	//判斷value是否為空值
        if (user != null){
        	//如果不為空值，就再判斷是否為團購主
        	if (user.getMemberClassify() == 1) {
        		//如果是團購主就進入活動專區
        		return "front-end/group/plan-activity3";
			}else {
				//如果不是就進入團購平台
				redirectAttributes.addFlashAttribute("pleaseLogin", "不是團購主 無法進入專區!");
        		return "redirect:/group-shop";
			}
    		
        }else {
        	//value為空值 就導向登入頁面
            redirectAttributes.addFlashAttribute("pleaseLogin", "請先登入!");
            session.setAttribute("location", planactivityurl);
            return "redirect:/login";
        }
	}
    @GetMapping("/single-product") //前台團購活動單品頁
	public String singleProduct(@RequestParam Integer groupActivityId, Model model) {
    	GroupActivityVO groupActivityVO = groupActivityService.showTheActivity(groupActivityId);
    	GroupProductVO tr = groupProductService.showOneProduct(groupActivityVO.getGroupProductId());
    	Long detailcount = groupActivityService.getDetailCount(groupActivityId);
    	
    	GroupActivityDTO temporarydto = new GroupActivityDTO();
    	Integer ActivityPrice = (int)(tr.getGroupProductPrice() * groupActivityVO.getGroupOrderDiscount());
		temporarydto.setGroupActivityPrice(ActivityPrice);
    	temporarydto.setGroupActivityId(groupActivityVO.getGroupActivityId());
		temporarydto.setGroupProductId(groupActivityVO.getGroupProductId());
		temporarydto.setGroupActivityContent(groupActivityVO.getGroupActivityContent());
		temporarydto.setGroupOrderStar(groupActivityVO.getGroupOrderStar());
		temporarydto.setGroupOrderEnd(groupActivityVO.getGroupOrderEnd());
		temporarydto.setGroupOrderMin(groupActivityVO.getGroupOrderMin());
		temporarydto.setGroupName(groupActivityVO.getGroupName());
		temporarydto.setGroupOrderDiscount(groupActivityVO.getGroupOrderDiscount());
//		temporarydto.setGroupProductPrice(tr.getGroupProductPrice());
		temporarydto.setGroupProductName(tr.getGroupProductName());
		temporarydto.setGroupProductContent(tr.getGroupProductContent());
		temporarydto.setGroupDetailCount(detailcount);
		GroupOrderMaster groupOrderMaster = groupActivityService.GiveMeNumberOfProduct(groupActivityId);
		temporarydto.setNumberOfProduct(groupOrderMaster.getNumberOfProduct());
		
    	 model.addAttribute("groupActivityDTO", temporarydto);
		return "front-end/group/single-product2";
	}
    @GetMapping("/group-product/updata") //後台團購商品管理-修改資料用頁面
    public String groupProductUpdata(@RequestParam Integer groupProductId, Model model, HttpSession session) {
    	 if (session.getAttribute("emp") != null) {
    		 //拿到id裡面資料
    		 GroupProductVO groupProductVO = groupProductService.showOneProduct(groupProductId);
    		 //將id裡面資料放入model
    		 model.addAttribute("groupProductVO", groupProductVO);
    		 //導至頁面
    		 return "back-end/group/group-product-updata2";
	        } else {
	            return "redirect:/employee/login";
	        }
    }
    @GetMapping("/group-product/Checkout") //結帳頁面
    public String groupCheckout(
    		@RequestParam Integer groupActivityId,
    		@RequestParam Integer groupActivityPrice,
    		Model model,
    		HttpSession session, RedirectAttributes redirectAttributes
    		) {
    	String checkouturl = "/group-product/Checkout?" + "groupActivityId=" + groupActivityId + "&groupActivityPrice=" + groupActivityPrice;
    	Members user = (Members) session.getAttribute("user");
        if (user != null){
        	Boolean examineDetail = groupActivityService.ConfirmDetail(groupActivityId, user.getMemberId());
        	if (examineDetail) {
        		redirectAttributes.addFlashAttribute("pleaseLogin", "已參加團購!");
        		return "redirect:/group-shop";
			}else {
				
				Integer memberId = user.getMemberId();
				String account = user.getMemberAccount();
				model.addAttribute("memberId", memberId);
				model.addAttribute("account", account);
				
				GroupActivityVO checkoutactivityvo = groupActivityService.showTheActivity(groupActivityId);
				GroupCheckoutDTO groupcheckoutDTO = new GroupCheckoutDTO();
				groupcheckoutDTO.setGroupOrderId(groupActivityId); //一個團購訂單對一個活動。
				groupcheckoutDTO.setGroupActivityId(groupActivityId);
				groupcheckoutDTO.setGroupActivityPrice(groupActivityPrice); //帶入活動價格。
				groupcheckoutDTO.setGroupName(checkoutactivityvo.getGroupName()); //帶入結帳明細顯示的活動名稱。
				model.addAttribute("groupcheckoutDTO", groupcheckoutDTO);
				
				return "front-end/group/group-Checkout";
			}
        }else {
            redirectAttributes.addFlashAttribute("pleaseLogin", "請先登入!");
            session.setAttribute("location", checkouturl);
            return "redirect:/login";
        }
    	
    }
//    @GetMapping("/plan-activity/updata") //前台團購主修改活動資料
//	public String theActivityUp(@RequestParam Integer groupActivityId, Model model) {
//    	GroupActivityVO groupActivityVO = groupActivityService.showTheActivity(groupActivityId);
//    	 model.addAttribute("groupActivityVO", groupActivityVO);
//		return "front-end/group/";
//    }
}
