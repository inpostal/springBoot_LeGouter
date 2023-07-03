package app.com.dessert5.controller;

import app.com.dessert5.service.DessertService;
import app.com.dessert5.vo.Dessert;
import app.com.emp.vo.Employee;
import app.com.member.vo.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/dessert5front")
@RestController
public class DessertShopFrontController {

    @Autowired
    private DessertService dessertService;

    @GetMapping("/findall")
    public List<Dessert> findAll() {
        List<Dessert> dessertList = dessertService.findAll();
        return dessertList;
    }

    @GetMapping("/getallcontent")
    public Dessert getAllContent(@RequestParam Integer dessertId) {
        Dessert dessert = dessertService.findById(dessertId);
        return dessert;
    }


    // 加入購物車
    @GetMapping("/addtocart")
    public String addToCart(@RequestParam Integer dessertId, HttpSession session) {
        Members member = (Members) session.getAttribute("user");
        String url = "dessertshop/shopAndproduct/sproduct.html?param=" + dessertId;

        // 如果沒登入過
        if (member == null){
            session.setAttribute("location", url );
            return "/login";
        }

        // 如果已登入
        Integer memberId = member.getMemberId(); // 從session中取得member再取得memberId
        return dessertService.addToCart(dessertId, memberId);
    }

    // 加入追蹤清單
    @GetMapping("/addtolovelist")
    public String addToLoveList(@RequestParam Integer dessertId, HttpSession session) {
        Members member = (Members) session.getAttribute("user");
        String url = "dessertshop/shopAndproduct/sproduct.html?param=" + dessertId;

        // 如果沒登入過
        if (member == null){
            session.setAttribute("location", url );
            return "/login";
        }

        // 如果已登入
        Integer memberId = member.getMemberId(); // 從session中取得member再取得memberId
        dessertService.addToLoveList(dessertId, memberId);
        return "已加入追蹤清單";
    }
}
