package app.com.dessertCart.controller;

import app.com.dessertCart.entity.DessertCartDTO;
import app.com.dessertCart.service.DessertCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String getDessertCartByMemberId(@PathVariable Integer memberId, Model model) {
        List<DessertCartDTO> dessertCartDTOList = dessertCartService.getDessertCartByMemberId(memberId);
        model.addAttribute("dessertCartList", dessertCartDTOList);
        return "/front-end/Dessert/DessertCart";
    }
}
