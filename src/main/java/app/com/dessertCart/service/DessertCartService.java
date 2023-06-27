package app.com.dessertCart.service;

import app.com.dessertCart.entity.DessertCart;

import java.util.List;

/**
 * ClassName: DessertCartService
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/27 PM 05:05
 */
public interface DessertCartService {
    DessertCart saveOrUpdateDessertCart(DessertCart dessertCart);

    void deleteDessertCart(Integer dessertId, Integer memId);

    DessertCart getDessertCart(Integer dessertId, Integer memId);

    List<DessertCart> getAllDessertCarts();
}