package app.com.dessertCart.service;

import app.com.dessertCart.entity.DessertCartDTO;

import java.util.List;

/**
 * ClassName: DessertCartService
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/27 PM 05:05
 */
public interface DessertCartService {

    List<DessertCartDTO> getDessertCartByMemberId(Integer memberId);

}
