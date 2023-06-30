package app.com.group.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import app.com.group.service.GroupActivityService;
import app.com.group.service.GroupProductImgService;
import app.com.group.service.GroupProductService;
import app.com.group.vo.GroupActivityDTO;
import app.com.group.vo.GroupActivityVO;
import app.com.group.vo.GroupProductDTO;
import app.com.group.vo.GroupProductImgVO;
import app.com.group.vo.GroupProductVO;
import app.com.group.vo.InserDetailDTO;
import app.com.grouporderdetail.vo.GroupOrderDetail;
import app.com.groupordermaster.vo.GroupOrderMaster;
import app.com.member.vo.Members;

@RestController
public class GroupRestContreller {

	@Autowired
	private GroupProductService groupProductService;

	@Autowired
	private GroupActivityService groupActivityService;

	@Autowired
	private GroupProductImgService groupProductImgService;

	// -----以下是團購商品-----
	// 後台 新增團購商品(舊)
	@PostMapping("/groupProduct/inser")
	public Map<String, Boolean> inserProduct(@RequestBody GroupProductDTO groupProductDTO) {
		Boolean success = groupProductService.inserProduct(groupProductDTO);
		Map<String, Boolean> response = new HashMap<>();
		response.put("success", success);
		return response;
	}

	// 後台瀏覽全部團購商品
	@GetMapping("/groupProduct/showList")
	public List<GroupProductVO> showAll() {
		return groupProductService.showAllProduct();
	}

	// 前台團購主專區發起檢視 與 可選團購商品
	@GetMapping("/groupProduct/showPutOnList")
	public List<GroupProductVO> showPutOnList() {
		return groupProductService.findPutOnList();
	}

	// 後台 查詢單筆團購商品
	@PostMapping("/groupProduct/showOne")
	public GroupProductVO showOne(@RequestParam Integer groupProductId) {
		// parseInt() 將String型別傳換成Integer包裝型別。
		// 前端抓取資料後如果做好Nunber轉換就不用了。
//		Integer gpid = Integer.parseInt(groupProductId);
		return groupProductService.showOneProduct(groupProductId);
	}

	// 後台 新增團購商品(含上傳單一圖片功能)
	@PostMapping("/groupProduct/inserNew")
	public Map<String, Boolean> inserProductNew(@RequestParam String groupProductName,
			@RequestParam String groupProductContent, @RequestParam Integer groupProductPrice,
			@RequestParam String groupProductStardate, @RequestParam Integer groupProductStatus,
			@RequestParam MultipartFile groupProductImg) {

		GroupProductDTO inserdto = new GroupProductDTO();
		inserdto.setGroupProductName(groupProductName);
		inserdto.setGroupProductContent(groupProductContent);
		inserdto.setGroupProductPrice(groupProductPrice);
		inserdto.setGroupProductStardate(groupProductStardate);
		inserdto.setGroupProductStatus(groupProductStatus);
		// 將商品資料存入資料庫，並取得save傳回的該商品VO物件。
		GroupProductVO evo = groupProductService.inserProductNew(inserdto);
		Boolean success1 = (evo != null);

		GroupProductImgVO inserivo = new GroupProductImgVO();
		try {
			// 取得該商品VO物件的AutoID，帶入成為FK之值。
			inserivo.setGroupProductId(evo.getGroupProductId());
			inserivo.setGroupProductImg(groupProductImg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 將FK及圖片資料存入資料庫。
		Boolean success2 = groupProductImgService.inserImg(inserivo);

		Map<String, Boolean> response = new HashMap<>();
		response.put("success1", success1);
		response.put("success2", success2);
		return response;
	}

	// 配合動態產生圖片連結。詳細問 救世主男哥。
	@GetMapping("/groupProduct/get/img/{groupProductId}")
	public ResponseEntity<Resource> getPicture(@PathVariable Integer groupProductId) {
		GroupProductImgVO gprimgyee = groupProductImgService.getImg(groupProductId);
		byte[] picture = gprimgyee.getGroupProductImg();

		ByteArrayResource resource = new ByteArrayResource(picture);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_GIF) // or another appropriate media type
				.body(resource);
	}

	// 後台 修改團購商品
	@PostMapping("/group-product/updata-the")
	public Map<String, Boolean> updataForProduct(@RequestParam Integer groupProductId,
			@RequestParam String groupProductName, @RequestParam String groupProductContent,
			@RequestParam Integer groupProductPrice, @RequestParam String groupProductStardate,
			@RequestParam String groupProductEnddate, @RequestParam Integer groupProductStatus,
			@RequestParam(required = false) MultipartFile groupProductImg) {
		GroupProductDTO updatato = new GroupProductDTO();
		updatato.setGroupProductId(groupProductId);
		updatato.setGroupProductName(groupProductName);
		updatato.setGroupProductContent(groupProductContent);
		updatato.setGroupProductPrice(groupProductPrice);
		updatato.setGroupProductStardate(groupProductStardate);
		updatato.setGroupProductEnddate(groupProductEnddate);
		updatato.setGroupProductStatus(groupProductStatus);
		Boolean success = groupProductService.updataThe(updatato);

		if (groupProductImg != null) {
			GroupProductImgVO updataivo = new GroupProductImgVO();
			try {
				updataivo.setGroupProductImgId(groupProductId);
				updataivo.setGroupProductImg(groupProductImg.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			groupProductImgService.updataImg(updataivo);
		}

		Map<String, Boolean> response = new HashMap<>();
		response.put("success", success);
		return response;
	}

	// -----以下是團購活動-----
	// 後台 瀏覽全部活動
	@GetMapping("/groupActivity/showList")
	public List<GroupActivityVO> showAllActivity() {
		return groupActivityService.allActivity();
	}

	// 前台 平台瀏覽全部活動
	@GetMapping("/group-shop/showActivityList")
	public List<GroupActivityDTO> showAllShopActivity() {
		// LocalDateTime.now()取得當下時間日期(含時區換算) > DateTimeFormatter.ofPattern("yyyy-MM-dd
		// HH:mm:ss").format()將當下時間格式化並轉成字串。
		String formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
//		System.out.println("測試當下時間String為:" + formatter);
		Date today = null;
		try {
			// 將String型別的日期資料轉成Date型別。
			today = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formatter);
//		System.out.println("測試當下時間Date為:" + today);
		} catch (ParseException e) {
			System.out.println("SimpleDateFormat錯誤");
		}
//		System.out.println("測試try完Date為:" + today);
		// 將時間放入參數。
		List<GroupActivityDTO> temporaryalldto = new ArrayList<GroupActivityDTO>();
		List<GroupActivityVO> temporaryvo = groupActivityService.allShopActivity(today);
		for (GroupActivityVO evo : temporaryvo) {
			GroupActivityDTO temporarydto = new GroupActivityDTO();
			GroupProductVO tr = groupProductService.showOneProduct(evo.getGroupProductId());
			Integer ActivityPrice = (int)(tr.getGroupProductPrice() * evo.getGroupOrderDiscount());
			temporarydto.setGroupActivityPrice(ActivityPrice);
			temporarydto.setGroupActivityId(evo.getGroupActivityId());
			temporarydto.setGroupProductId(evo.getGroupProductId());
			temporarydto.setGroupActivityContent(evo.getGroupActivityContent());
			temporarydto.setGroupOrderStar(evo.getGroupOrderStar());
			temporarydto.setGroupOrderEnd(evo.getGroupOrderEnd());
			temporarydto.setGroupOrderMin(evo.getGroupOrderMin());
			temporarydto.setGroupName(evo.getGroupName());
			temporarydto.setGroupOrderDiscount(evo.getGroupOrderDiscount());
			temporarydto.setGroupProductPrice(tr.getGroupProductPrice());
//			temporarydto.setGroupName(tr.getGroupProductName());
//			temporarydto.setGroupProductContent(tr.getGroupProductContent());
			temporaryalldto.add(temporarydto);
		}
//		return groupActivityService.allShopActivity(today);
		return temporaryalldto;
	}

	// 前台單品 *改成thymeleaf後已無用*
	@PostMapping("/single-product/showthe")
	public GroupActivityVO showthe(@RequestParam String groupActivityId) {
		Integer theid = Integer.parseInt(groupActivityId);
		return groupActivityService.showTheActivity(theid);
	}

	// 前台團購主專區 發起活動
	@PostMapping("/groupActivity/inser")
	public Map<String, Boolean> inserActivity(@RequestBody GroupActivityDTO groupActivityDTO, HttpSession session) {
		System.out.println("開始日期" + groupActivityDTO.getGroupOrderStar());
		System.out.println("結束日期" + groupActivityDTO.getGroupOrderEnd());
//		Members user = (Members) session.getAttribute("user");
//		String address = user.getMemberAddress();
		
		GroupActivityVO theacvo = groupActivityService.inserActivitys(groupActivityDTO);
		Boolean success = (theacvo != null);
		
		GroupOrderMaster masterdto = new GroupOrderMaster();
		System.out.println("剛剛新增的活動編號" + theacvo.getGroupActivityId());
//		masterdto.setGroupOrderId(theacvo.getGroupActivityId()); //設定團購訂單PK編號，一個活動對應一個團購訂單主檔。 20230630下面已經有FK了 改自動生成PK。
//		masterdto.setMemId(user.getMemberId()); //設定團購主編號
		masterdto.setMemId(2); //測試設定團購主編號
		masterdto.setGroupActivityId(theacvo.getGroupActivityId()); //設定FK活動編號
		masterdto.setNumberOfProduct(0); //設定初始購買商品數量
		masterdto.setGroupOrderStatus(1); //設定初始團購狀態
		masterdto.setGroupOrderBonus(null); //設定初始可獲得分潤金額
		masterdto.setTotalGroupProductPrice(null); //設定初始團購單總金額
		masterdto.setGroupOrderBonusStatus(0); //設定初始分潤發放狀態
		System.out.println(masterdto);
		GroupOrderMaster getthevo = groupActivityService.inserMaster(masterdto);
		
		 Map<String, Boolean> response = new HashMap<>();
		 response.put("success", success);
		return response;
	}

	// 後台 刪除活動用
	@PostMapping("/groupActivity/deleteone")
	public Map<String, Boolean> deleteOne(@RequestParam String groupActivityId) {
		Integer theid = Integer.parseInt(groupActivityId);
		Boolean success = groupActivityService.deleteOneActivity(theid);
		Map<String, Boolean> response = new HashMap<>();
		response.put("success", success);
		return response;
	}
	
	//前台 結帳資料
	@PostMapping("/groupActivity/CheckoutDetail")
	public Map<String, Boolean> InserDetail(@RequestBody InserDetailDTO inserDetailDTO) {
//		System.out.println("第一層觀察抓到的數量:" + inserDetailDTO.getGroupOrderAmount());
		GroupOrderDetail retuvo = groupActivityService.inDetail(inserDetailDTO);
		Boolean success = (retuvo != null);
		Map<String, Boolean> response = new HashMap<>();
		response.put("success", success);
		return response;
	}
	
	// 前台團購主修改活動資料
//	@PostMapping("/plan-activity/updata-the")
//	public Map<String, Boolean> updataForActivity(
//			@RequestParam("groupActivityId") Integer groupActivityId,
//			@RequestParam("groupActivityContent") String groupActivityContent,
//			@RequestParam("groupName") String groupName
//			) {
//		GroupActivityDTO updatato = new GroupActivityDTO();
//		updatato.setGroupActivityId(groupActivityId);
//		updatato.setGroupActivityContent(groupActivityContent);
//		updatato.setGroupName(groupName);
//		
//		Boolean success = groupActivityService.updataActivity(updatato);
//		
//		Map<String, Boolean> response = new HashMap<>();
//		 response.put("success", success);
//		return response;
//	}
}
