package app.com.dessertCart.service.impl;

import app.com.dessert5.dao.DessertRepository;
import app.com.dessert5.vo.Dessert;
import app.com.dessertCart.integration.AllInOne;
import app.com.dessertCart.integration.domain.AioCheckOutALL;
import app.com.dessertOrderDetail.entity.OrderDetail;
import app.com.dessertOrderDetail.repository.OrderDetailRepository;
import app.com.dessertOrders.entity.Orders;
import app.com.dessertOrders.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: payment
 * Description:
 *
 * @Author Charlie
 * @Create 2023/7/4 PM 04:04
 */
@Service
public class PaymentServiceimpl implements app.com.dessertCart.service.PaymentService {
    public AllInOne all = new AllInOne("");

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    DessertRepository dessertRepository;

    public String genAioCheckOutALL(Integer orderid) {
        Orders orders = ordersRepository.findById(orderid).orElse(null);
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderid);


        StringBuilder itemName = new StringBuilder();
        for (OrderDetail orderDetail : orderDetails) {
            Dessert dessert = dessertRepository.findById(orderDetail.getDessertId()).orElse(null);
            String dessertName = dessert.getDessertName();
            int dessertAmount = orderDetail.getDessertAmount();


            itemName.append(dessertName).append("*").append(dessertAmount).append("，");
        }


        Integer orderTotal = orders.getCpOrderTotal();


        AioCheckOutALL obj = new AioCheckOutALL();
        obj.setMerchantTradeNo("G6LeGouter" + orderid);   //訂單編號
        obj.setMerchantTradeDate("2017/01/01 08:05:23");  //訂單日期
        obj.setTotalAmount(String.valueOf(orderTotal));   //訂單全部總金額
        obj.setTradeDesc("test Description");
        obj.setItemName(itemName.toString());
        obj.setReturnURL("http://211.23.128.214:5000");
//		obj.setOrderResultURL("http://127.0.0.1:5502");
//		obj.setOrderResultURL("https://www.google.com.tw/");
        obj.setOrderResultURL("http://localhost:8080/OrderSuccessful");  //付款完跳轉的頁面

        obj.setNeedExtraPaidInfo("N");
        String form = all.aioCheckOut(obj, null);
        return form;
    }
}
