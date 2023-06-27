package app.com.dessertCart.service.impl;

import app.com.dessertCart.entity.DessertCart;
import app.com.dessertCart.entity.DessertCartId;
import app.com.dessertCart.repository.DessertCartRepository;
import app.com.dessertCart.service.DessertCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public DessertCart saveOrUpdateDessertCart(DessertCart dessertCart) {
        return dessertCartRepository.save(dessertCart);
    }

    @Override
    public void deleteDessertCart(Integer dessertId, Integer memId) {
        DessertCartId dessertCartId = new DessertCartId(dessertId, memId);
        dessertCartRepository.deleteById(dessertId);
    }

    @Override
    public DessertCart getDessertCart(Integer dessertId, Integer memId) {
        DessertCartId dessertCartId = new DessertCartId(dessertId, memId);
        Optional<DessertCart> optionalDessertCart = dessertCartRepository.findById(dessertId);
        return optionalDessertCart.orElse(null);
    }

    @Override
    public List<DessertCart> getAllDessertCarts() {
        return dessertCartRepository.findAll();
    }
}
