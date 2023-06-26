package app.com.grouporderdetail.controller;

import app.com.grouporderdetail.service.GroupOrderDetailService;
import app.com.grouporderdetail.vo.GroupOrderDetail;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class GroupOrderDetailController {
    @Autowired
    private GroupOrderDetailService service;

    //用複合主鍵取得資料
    @GetMapping("/grouporderdetail/list/getbyAll")
    public String groupOrderDetailgetAll(){
        return "/back-end/groupOrder/groupOrderDetail";
    }
    //用複合主鍵取得資料
    //訂單主檔要對到訂單明細
@GetMapping("/grouporderdetail/list/getbyId/all")
    public String getGroupOrderDetailById(@RequestParam Integer groupOrderId, Model model){
    List<GroupOrderDetail> list = service.getGroupOrderDetailById(groupOrderId);
    model.addAttribute("groupOrderDetail",list);
        return "/back-end/groupOrder/groupOrderDetail";
    }
    //修該訂單明細個人資料==>用複合主鍵查詢
    //thyleaf grouporderdetail
    @GetMapping("/grouporderdetail/update/thyleaf")
    public String updategrouporderdetail(@RequestParam Integer groupOrderId,@RequestParam Integer memId, Model model) {
        // 拿到id裡面資料
        GroupOrderDetail updategrouporderdetail=service.getOrderdetail(groupOrderId, memId);
        System.out.println(updategrouporderdetail);
        // 將id裡面資料放入model
        model.addAttribute("groupOrderDetail", updategrouporderdetail);
        // 導至頁面
        return "/back-end/groupOrder/updateGroupOrderDetail";

    }
@PostMapping("/grouporderdetail/update")
    public ResponseEntity<?>update(@RequestParam("grouporderid")Integer groupOrderId,
                                   @RequestParam("memId")Integer memberId,
@RequestParam("grouporderamount")Integer groupOrderAmount,
@RequestParam("groupproductprice")Integer groupProductPrice,
@RequestParam("receivername")String receiverName,
@RequestParam("receiveremail")String receiverEmail,
@RequestParam("receiverphone")String receiverPhone,
@RequestParam("receiveraddress")String receiverAddress,
@RequestParam("groupproductother")String groupProductOther,
@RequestParam("groupproductstatus")byte groupProductStatus,
@RequestParam("groupproductpaying")byte groupProductPaying ){
        GroupOrderDetail groupOrderDetail = new GroupOrderDetail();
groupOrderDetail.setGroupOrderId(groupOrderId);
groupOrderDetail.setMemberId(memberId);
groupOrderDetail.setGroupOrderAmount(groupOrderAmount);
groupOrderDetail.setGroupProductPrice(groupProductPrice);
groupOrderDetail.setReceiverName(receiverName);
groupOrderDetail.setReceiverEmail(receiverEmail);
groupOrderDetail.setReceiverPhone(receiverPhone);
groupOrderDetail.setReceiverAddress(receiverAddress);
groupOrderDetail.setGroupProductOthers(groupProductOther);
groupOrderDetail.setGroupProductStatus(groupProductStatus);
groupOrderDetail.setGroupProductPaying(groupProductPaying);
        service.update(groupOrderDetail);
        return ResponseEntity.ok("ok");
    }



}




