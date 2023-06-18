package app.com.group.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import app.com.group.service.GroupActivityService;
import app.com.group.service.GroupProductImgService;
import app.com.group.service.GroupProductService;
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
	@PostMapping("/groupProduct/inser")
	public Map<String, Boolean> inserProduct(@RequestBody GroupProductDTO groupProductDTO) {
		Boolean success = groupProductService.inserProduct(groupProductDTO);
		 Map<String, Boolean> response = new HashMap<>();
		 response.put("success", success);
		return response;
	}
	
	@GetMapping("/groupProduct/showList")
	public List<GroupProductVO> showAll() {
		return groupProductService.showAllProduct();
	}
	
	@PostMapping("/groupProduct/showOne")
	public GroupProductDTO showOne (@RequestParam String groupProductId) {
		Integer gpid = Integer.parseInt(groupProductId);
		return groupProductService.showOneProduct(gpid);
	}
	
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

	
	//-----以下是團購活動-----
	@GetMapping("/groupActivity/showList")
	public List<GroupActivityVO> showAllActivity() {
		return groupActivityService.allActivity();
	}

}