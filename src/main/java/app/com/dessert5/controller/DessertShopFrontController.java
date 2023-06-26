package app.com.dessert5.controller;

import app.com.dessert5.service.DessertService;
import app.com.dessert5.vo.Dessert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/dessert5front")
@RestController
public class DessertShopFrontController {

    @Autowired
    private DessertService dessertService;

    @GetMapping("/findall")
    public List<Dessert> findAll() {
        List<Dessert> dessertList = dessertService.findAll();
        return dessertList;
    }

    @GetMapping("/getallcontent")
    public Dessert getAllContent(@RequestParam Integer dessertId) {
        Dessert dessert = dessertService.findById(dessertId);
        return dessert;
    }
}
