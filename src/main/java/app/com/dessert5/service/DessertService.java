package app.com.dessert5.service;

import app.com.dessert5.dao.Dessert5CartRepository;
import app.com.dessert5.dao.Dessert5LoveListRepository;
import app.com.dessert5.dao.DessertRepository;
import app.com.dessert5.vo.Dessert;
import app.com.dessert5.vo.Dessert5Cart;
import app.com.dessert5.vo.Dessert5CartPK;
import app.com.dessert5.vo.Dessert5LoveList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DessertService {

    @Autowired
    private DessertRepository dessertRepository;

    @Autowired
    private Dessert5CartRepository dessert5CartRepository;

    @Autowired
    private Dessert5LoveListRepository dessert5LoveListRepository;


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
            result = "商品名稱已存在，新增失敗";
        }

        return result;
    }

    public Dessert findByName(String dessertName){
        Dessert dessert = dessertRepository.findByDessertName(dessertName);
        return dessert;
    }

    public List<Dessert> findByDessertNameContaining(String dessertName){
        return dessertRepository.findByDessertNameContaining(dessertName);
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
            return "商品名稱已存在，無法修改";
        } else {
            dessertOld.setDessertName(dessert.getDessertName());
            dessertOld.setDessertContent(dessert.getDessertContent());
            dessertOld.setDessertPrice(dessert.getDessertPrice());
            dessertOld.setDessertStatus(dessert.getDessertStatus());
            dessertRepository.save(dessertOld);
            return "編輯成功";
        }

    }


    // 加入購物車(+1)
    public String addToCart(Integer dessertId, Integer memberId){
        Dessert5CartPK dessert5CartPK = new Dessert5CartPK(dessertId, memberId);
        Dessert5Cart dessert5Cart = dessert5CartRepository.findById(dessert5CartPK).orElse(null);


        if (dessert5Cart == null) {
            Dessert5Cart newDessert5Cart = new Dessert5Cart();
            newDessert5Cart.setId(dessert5CartPK);
            newDessert5Cart.setQuantity(1);
            dessert5CartRepository.save(newDessert5Cart);
            return "已加入購物車";
        } else {
            Integer q = dessert5Cart.getQuantity();
            if (q >= 50) {
                return "購物車數量已達上限，無法再新增";
            } else {
                dessert5Cart.setQuantity(q + 1);
                dessert5CartRepository.save(dessert5Cart);
                return "已加入購物車";
            }
        }

    }

    // 加入追蹤清單
    public void addToLoveList(Integer dessertId, Integer memberId){
        Dessert5LoveList dessert5LoveListKey = new Dessert5LoveList(dessertId, memberId);
        Dessert5LoveList olddessert5LoveList = dessert5LoveListRepository.findById(dessert5LoveListKey).orElse(null);


        if (olddessert5LoveList == null) {
            Dessert5LoveList newDessert5LoveList = new Dessert5LoveList();
            newDessert5LoveList.setDessertId(dessertId);
            newDessert5LoveList.setMemberId(memberId);
            dessert5LoveListRepository.save(newDessert5LoveList);
        } else {
            return;
        }

    }
}
