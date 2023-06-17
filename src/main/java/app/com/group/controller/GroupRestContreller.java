package app.com.group.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.com.group.service.GroupProductService;
import app.com.group.vo.GroupProductDTO;

@RestController
public class GroupRestContreller {

	@Autowired
	private GroupProductService groupProductService;

	@PostMapping("/groupProduct/inser")
	public Map<String, Boolean> inserProduct(@RequestBody GroupProductDTO groupProductDTO) {
		Boolean success = groupProductService.inserProduct(groupProductDTO);
		 Map<String, Boolean> response = new HashMap<>();
		 response.put("success", success);
		return response;
	}

}
