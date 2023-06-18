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

//    public Dessert findByPK(Integer dessertId){
//        Dessert dessert = dessertRepository.findById(dessertId).orElse(null);
//        return dessert;
//    }

    public String update(Integer dessertId, Dessert dessert){

        // 找到要被編輯的商品
        Dessert dessertOld = dessertRepository.findById(dessertId).orElse(null);

        // 檢查新編輯的商品資訊，是否有重複的商品名稱
        if (dessertRepository.findByDessertName(dessert.getDessertName()) == null) {
            dessert.setDessertId(dessertId);
            dessertRepository.save(dessert);
            return "編輯成功";
        } else {
            return "商品已存在，無法修改";
        }


    }
}
