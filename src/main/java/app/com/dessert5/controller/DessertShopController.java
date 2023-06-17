package app.com.dessert5.controller;

import app.com.dessert5.service.DessertService;
import app.com.dessert5.vo.Dessert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dessert5")
@RestController
public class DessertShopController {

    @Autowired
    private DessertService dessertService;


    // 測試連線
    @GetMapping("/test")
    public String test(){
        return "test success!";
    }


    @GetMapping ("/findAll")
    public List<Dessert> findAll(){
        List<Dessert> dessertList = dessertService.findAll();
        return dessertList;
    }


    @GetMapping("/soldout")
    public void soldOut(@RequestParam String dessertName){
        System.out.println(dessertName);
        dessertService.soldout(dessertName);
    }

    @PostMapping("/insert")
    public String insert(@RequestBody Dessert dessert){
        System.out.println(dessert);
        String msg = dessertService.insert(dessert);
        return msg;
    }

    @GetMapping("/search")
    public Dessert search(@RequestParam String dessertName){
        Dessert dessert = dessertService.findByName(dessertName);
        return dessert;
    }

    @PutMapping("/update/{dessertName}")
    public String update(@PathVariable String dessertName,
                         @RequestBody Dessert dessert){
        System.out.println("dessertName: " + dessertName);
        String msg = dessertService.update(dessertName, dessert);
        return msg;
    }

}
