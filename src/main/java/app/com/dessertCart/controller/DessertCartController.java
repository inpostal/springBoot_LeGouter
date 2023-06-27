package app.com.dessertCart.controller;

import app.com.dessertCart.entity.DessertCart;
import app.com.dessertCart.entity.DessertCartId;
import app.com.dessertCart.service.DessertCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: DessertCartController
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/27 PM 05:22
 */

@RestController
@RequestMapping("/cart")
public class DessertCartController {
    private final DessertCartService dessertCartService;

    @Autowired
    public DessertCartController(DessertCartService dessertCartService) {
        this.dessertCartService = dessertCartService;
    }

    @GetMapping("/detail")
    public DessertCart getDessertCart(@RequestBody DessertCartId dessertCartId) {
        Integer dessertId = dessertCartId.getDessertId();
        Integer memId = dessertCartId.getMemId();
        return dessertCartService.getDessertCart(dessertId, memId);
    }

    @PostMapping("/update-amount")
    public DessertCart updateDessertCartAmount(@RequestBody DessertCart dessertCart) {
        return dessertCartService.saveOrUpdateDessertCart(dessertCart);
    }

    @GetMapping("/delete")
    public void deleteDessertCart(@RequestParam("dessertId") Integer dessertId, @RequestParam("memId") Integer memId) {
        dessertCartService.deleteDessertCart(dessertId, memId);
    }

    @GetMapping("/get-all")
    public List<DessertCart> getAllDessertCarts() {
        return dessertCartService.getAllDessertCarts();
    }
}
