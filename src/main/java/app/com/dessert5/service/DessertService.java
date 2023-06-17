package app.com.dessert5.service;

import app.com.dessert5.dao.DessertRepository;
import app.com.dessert5.vo.Dessert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DessertService {

    @Autowired
    private DessertRepository dessertRepository;


    public List<Dessert> findAll(){
        List<Dessert> dessertList = (List)dessertRepository.findAll();
        return dessertList;
    }

    public void soldout(String dessertName){
        Dessert dessert = dessertRepository.findByDessertName(dessertName);
        dessert.setDessertStatus("已下架");
        dessertRepository.save(dessert);
    }

    public String insert(Dessert dessert) {
        String result;
        Dessert d = dessertRepository.findByDessertName(dessert.getDessertName());
        // 如果沒有重複的甜點名稱，就新增
        if (d == null) {
            dessertRepository.save(dessert);
            result = "新增成功";
        } else {
            result = "商品已存在，無法新增";
        }
        return result;
    }

    public Dessert findByName(String dessertName){
        Dessert dessert = dessertRepository.findByDessertName(dessertName);
        return dessert;
    }

    public String update(String dessertName, Dessert dessert){
        // 要被編輯的商品
        System.out.println("dessertName2: " + dessertName);
        Dessert d = dessertRepository.findByDessertName(dessertName);

        // 新編輯的商品資訊，是否有重複的商品名稱
        Dessert t = dessertRepository.findByDessertName(dessert.getDessertName());
        System.out.println("dessertName3: " + dessert.getDessertName());
        if (t != null){
            return "商品已存在，無法修改";
        } else {
            dessertRepository.save(d);
            return "編輯成功";
        }
    }
}
