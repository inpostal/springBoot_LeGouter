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

    public Dessert findById(Integer dessertId){
        Dessert dessert = dessertRepository.findById(dessertId).orElse(null);
        return dessert;
    }

    // 編輯一筆
    public String update(Integer dessertId, Dessert dessert){

        // 找到要被編輯的商品
        Dessert dessertOld = dessertRepository.findById(dessertId).orElse(null);

        // sameNameDessert: 輸入的甜點名稱是否已存在
        Dessert sameNameDessert = dessertRepository.findByDessertName(dessert.getDessertName());

        if ( (sameNameDessert != null) && (sameNameDessert.getDessertId() != dessertId) ) {
            return "商品已存在，無法修改";
        } else {
            dessertOld.setDessertName(dessert.getDessertName());
            dessertOld.setDessertContent(dessert.getDessertContent());
            dessertOld.setDessertPrice(dessert.getDessertPrice());
            dessertOld.setDessertStatus(dessert.getDessertStatus());
            dessertRepository.save(dessertOld);
            return "編輯成功";
        }

    }
}
