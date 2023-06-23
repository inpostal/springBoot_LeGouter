package app.com.coupon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CouponController {

    @GetMapping("/coupon/manage")
    public String couponManage(){
        return "/back-end/Coupon/CouponManage";
    }

    @GetMapping("/coupon/add")
    public String couponAdd(){
        return "/back-end/Coupon/CouponAdd";
    }
    @GetMapping("/coupon/edit")
    public String couponEdit(){
        return "/back-end/Coupon/CouponEdit";
    }
}
