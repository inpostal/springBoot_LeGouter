package app.com.grouporderdetail.controller;

import app.com.grouporderdetail.service.GroupOrderDetailService;
import app.com.grouporderdetail.vo.GroupOrderDetail;

import app.com.member.vo.Members;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GroupOrderDetailController {
    @Autowired
    private GroupOrderDetailService service;

    //後臺訂單明細列表
    //用複合主鍵取得資料
    @GetMapping("/grouporderdetail/list/getbyAll")
    public String groupOrderDetailgetAll() {
        return "/back-end/groupOrder/groupOrderDetail";
    }

    //用複合主鍵取得資料
    //訂單主檔要對到訂單明細
    @GetMapping("/grouporderdetail/list/getbyId/all")
    public String getGroupOrderDetailById(@RequestParam Integer groupOrderId, Model model) {
        List<GroupOrderDetail> list = service.getGroupOrderDetailById(groupOrderId);
        model.addAttribute("groupOrderDetail", list);
        return "/back-end/groupOrder/groupOrderDetail";
    }

    //後台查詢訂單者資訊
    //修該訂單明細個人資料==>用複合主鍵查詢
    //thyleaf grouporderdetail
    @GetMapping("/grouporderdetail/update/thyleaf")
    public String updategrouporderdetail(@RequestParam Integer groupOrderId, @RequestParam Integer memId, Model model) {
        // 拿到id裡面資料
        GroupOrderDetail updategrouporderdetail = service.getOrderdetail(groupOrderId, memId);
        System.out.println(updategrouporderdetail);
        // 將id裡面資料放入model
        model.addAttribute("groupOrderDetail", updategrouporderdetail);
        // 導至頁面
        return "/back-end/groupOrder/updateGroupOrderDetail";

    }

    //後台修改訂單者資訊
    @PostMapping("/grouporderdetail/update")
    public ResponseEntity<?> update(@RequestParam("grouporderid") Integer groupOrderId,
                                    @RequestParam("memId") Integer memberId,
                                    @RequestParam("grouporderamount") Integer groupOrderAmount,
                                    @RequestParam("groupproductprice") Integer groupProductPrice,
                                    @RequestParam("receivername") String receiverName,
                                    @RequestParam("receiveremail") String receiverEmail,
                                    @RequestParam("receiverphone") String receiverPhone,
                                    @RequestParam("receiveraddress") String receiverAddress,
                                    @RequestParam("groupproductother") String groupProductOther,
                                    @RequestParam("groupproductstatus") byte groupProductStatus,
                                    @RequestParam("groupproductpaying") byte groupProductPaying) {
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


    //前台查詢個人訂單明細(團購主專區),用memberId去抓到資料
    @GetMapping("/grouporderdetail/list/getbyId/front")
    @ResponseBody
    public List<GroupOrderDetail> getGroupOrderDetailByIdFront(@RequestParam Integer memberId) {
        return  service.getAllByMemberId(memberId);
    }
    //團購主專區前台
    @GetMapping("/grouporderdetail/list/front")
    public String frontGroupOrderDetail(Model model, HttpSession session) {
//        Members user = (Members) session.getAttribute("user");
//        model.addAttribute("user", user);
        return "/front-end/grouporder/groupOrderEmp";
    }
    //一般會員專區
}





