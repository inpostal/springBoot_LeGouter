package app.com.dessertCart.service.impl;

import app.com.dessertCart.entity.DessertCart;
import app.com.dessertCart.repository.DessertCartRepository;
import app.com.dessertCart.service.DessertCartService;
import app.com.member.vo.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: DessertCartServiceImpl
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/27 PM 05:12
 */
@Service
public class DessertCartServiceImpl implements DessertCartService {

    @Autowired
    private DessertCartRepository dessertCartRepository;

    @Override
    public List<DessertCart> getDessertCartByMember(Members member) {
        return dessertCartRepository.findByMember(member);
    }

    @Override
    public double calculateTotalPrice(List<DessertCart> dessertCartItems) {
        double totalPrice = 0.0;
        for (DessertCart item : dessertCartItems) {
            if (item.getDessert() != null) {
                totalPrice += item.getDessert().getDessertPrice() * item.getCartDessertQuantity();
            }
        }
        return totalPrice;
    }
}