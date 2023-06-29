package app.com.dessertCart.controller;

import app.com.dessertCart.entity.DessertCartDTO;
import app.com.dessertCart.service.DessertCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ClassName: DessertCartController
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/27 PM 05:22
 */

@Controller
public class DessertCartController {
    @Autowired
    private DessertCartService dessertCartService;


    @GetMapping("/dessertCart/{memberId}")
    public String getDessertCartByMemberId() {
        return "/front-end/Dessert/DessertCart";
    }

    @GetMapping("/dessertCart/get/{memberId}")
    @ResponseBody
    public List<DessertCartDTO> getDessertCartByMemberId(@PathVariable Integer memberId) {
        List<DessertCartDTO> dessertCartDTOList = dessertCartService.getDessertCartByMemberId(memberId);
        return dessertCartDTOList;
    }

//    @PostMapping("dessertCart/update")
//    public ResponseEntity<?> updateDessertCartQuantity(@RequestParam Integer dessertId,
//                                                       @RequestParam Integer memberId,
//                                                       @RequestParam Integer cartDessertQuantity) {
//        dessertCartService.updateDessertCartQuantity(dessertId, memberId, cartDessertQuantity);
//        return ResponseEntity.ok().build();
//    }
}
