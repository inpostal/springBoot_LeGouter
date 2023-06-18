package app.com.dessert5.controller;

import app.com.dessert5.service.DessertService;
import app.com.dessert5.vo.Dessert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    // 查詢所有甜點
    @GetMapping ("/findAll")
    public List<Dessert> findAll(){
        List<Dessert> dessertList = dessertService.findAll();
        return dessertList;
    }

    // 下架一筆
    @GetMapping("/soldout")
    public void soldOut(@RequestParam String dessertName){
        System.out.println(dessertName);
        dessertService.soldout(dessertName);
    }

    // 新增一筆
    @PostMapping("/insert")
    public String insert(@RequestBody Dessert dessert){
        System.out.println(dessert);
        String msg = dessertService.insert(dessert);
        return msg;
    }

    // 查詢一筆
    @GetMapping("/search")
    public Dessert search(@RequestParam String dessertName){
        Dessert dessert = dessertService.findByName(dessertName);
        return dessert;
    }

    // 編輯一筆
    @PutMapping("/update/{dessertId}")
    public String update(@PathVariable Integer dessertId,
                         @RequestBody Dessert dessert){

        String msg = dessertService.update(dessertId, dessert);
        return msg;
    }

    // 存圖片
    @PutMapping("/upload/{dessertId}")
    public String upload(@RequestBody MultipartFile file,
                         @PathVariable Integer dessertId){
        System.out.println("接到請求");
        String msg = "";
        return msg;
    }
}
