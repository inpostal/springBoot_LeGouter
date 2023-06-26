package app.com.group.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@RestController
public class GroupRestContreller {

	@Autowired
	private GroupProductService groupProductService;
	
	@Autowired
	private GroupActivityService groupActivityService;
	
	@Autowired
	private GroupProductImgService groupProductImgService;

	//-----以下是團購商品-----
	//後台 新增團購商品(舊)
	@PostMapping("/groupProduct/inser")
	public Map<String, Boolean> inserProduct(@RequestBody GroupProductDTO groupProductDTO) {
		Boolean success = groupProductService.inserProduct(groupProductDTO);
		 Map<String, Boolean> response = new HashMap<>();
		 response.put("success", success);
		return response;
	}
	
	//後台瀏覽全部團購商品
	@GetMapping("/groupProduct/showList")
	public List<GroupProductVO> showAll() {
		return groupProductService.showAllProduct();
	}
	
	//查詢單以團購商品
	@PostMapping("/groupProduct/showOne")
	public GroupProductVO showOne (@RequestParam String groupProductId) {
		Integer gpid = Integer.parseInt(groupProductId);
		return groupProductService.showOneProduct(gpid);
	}
	
	//後台 新增團購商品(含上傳單一圖片功能)
	@PostMapping("/groupProduct/inserNew")
	public Map<String, Boolean> inserProductNew(
			@RequestParam("groupProductName") String groupProductName,
			@RequestParam("groupProductContent") String groupProductContent,
			@RequestParam("groupProductPrice") Integer groupProductPrice,
			@RequestParam("groupProductStardate") String groupProductStardate,
			@RequestParam("groupProductStatus") Integer groupProductStatus,
			@RequestParam("groupProductImg") MultipartFile groupProductImg) {
		
		GroupProductDTO inserdto = new GroupProductDTO();
		inserdto.setGroupProductName(groupProductName);
		inserdto.setGroupProductContent(groupProductContent);
		inserdto.setGroupProductPrice(groupProductPrice);
		inserdto.setGroupProductStardate(groupProductStardate);
		inserdto.setGroupProductStatus(groupProductStatus);
		Boolean success1 = groupProductService.inserProductNew(inserdto);
		
		GroupProductImgVO inserivo = new GroupProductImgVO();
		try {
			inserivo.setGroupProductImg(groupProductImg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Boolean success2 = groupProductImgService.inserImg(inserivo);
		
		
		 Map<String, Boolean> response = new HashMap<>();
		 response.put("success1", success1);
		 response.put("success2", success2);
		return response;
	}
	
	//配合動態產生圖片連結。詳細問 救世主男哥。
	@GetMapping("/groupProduct/get/img/{groupProductId}")
    public ResponseEntity<Resource> getPicture(@PathVariable Integer groupProductId) {
		GroupProductImgVO gprimgyee = groupProductImgService.getImg(groupProductId);
        byte[] picture = gprimgyee.getGroupProductImg();

        ByteArrayResource resource = new ByteArrayResource(picture);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_GIF) // or another appropriate media type
                .body(resource);
    }

	//後台 修改團購商品
	@PostMapping("/group-product/updata-the")
	public Map<String, Boolean> updataForProduct(
			@RequestParam("groupProductId") Integer groupProductId,
			@RequestParam("groupProductName") String groupProductName,
			@RequestParam("groupProductContent") String groupProductContent,
			@RequestParam("groupProductPrice") Integer groupProductPrice,
			@RequestParam("groupProductStardate") String groupProductStardate,
			@RequestParam("groupProductEnddate") String groupProductEnddate,
			@RequestParam("groupProductStatus") Integer groupProductStatus,
			@RequestParam("groupProductImg") MultipartFile groupProductImg) {
		GroupProductDTO updatato = new GroupProductDTO();
		updatato.setGroupProductId(groupProductId);
		updatato.setGroupProductName(groupProductName);
		updatato.setGroupProductContent(groupProductContent);
		updatato.setGroupProductPrice(groupProductPrice);
		updatato.setGroupProductStardate(groupProductStardate);
		updatato.setGroupProductEnddate(groupProductEnddate);
		updatato.setGroupProductStatus(groupProductStatus);
		Boolean success = groupProductService.updataThe(updatato);
		
		GroupProductImgVO updataivo = new GroupProductImgVO();
		try {
			updataivo.setGroupProductImgId(groupProductId);
			updataivo.setGroupProductImg(groupProductImg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		groupProductImgService.updataImg(updataivo);
		
		Map<String, Boolean> response = new HashMap<>();
		 response.put("success", success);
		return response;
	}
	
	//-----以下是團購活動-----
	//後台 瀏覽全部活動
	@GetMapping("/groupActivity/showList")
	public List<GroupActivityVO> showAllActivity() {
		return groupActivityService.allActivity();
	}
	
	//前台 瀏覽全部活動
	@GetMapping("/group-shop/showActivityList")
	public List<GroupActivityVO> showAllShopActivity() {
		return groupActivityService.allShopActivity();
	}

	//前台單品
	@PostMapping("/single-product/showthe")
	public GroupActivityVO showthe (@RequestParam String groupActivityId) {
		Integer theid = Integer.parseInt(groupActivityId);
		return groupActivityService.showTheActivity(theid);
	}
	
	//前台團購主專區
	@PostMapping("/groupActivity/inser")
	public Map<String, Boolean> inserActivity(@RequestBody GroupActivityDTO groupActivityDTO) {
		System.out.println("開始日期" + groupActivityDTO.getGroupOrderStar());
		System.out.println("結束日期" + groupActivityDTO.getGroupOrderEnd());
		Boolean success = groupActivityService.inserActivitys(groupActivityDTO);
		 Map<String, Boolean> response = new HashMap<>();
		 response.put("success", success);
		return response;
	}
	
	//後台
	@PostMapping("/groupActivity/deleteone")
	public Map<String, Boolean> deleteOne (@RequestParam String groupActivityId) {
		Integer theid = Integer.parseInt(groupActivityId);
		Boolean success = groupActivityService.deleteOneActivity(theid);
		Map<String, Boolean> response = new HashMap<>();
		 response.put("success", success);
		return response;
	}
}
