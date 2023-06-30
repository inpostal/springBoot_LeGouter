package app.com.dessertCart.service.impl;

import app.com.dessert5.dao.DessertRepository;
import app.com.dessert5.vo.Dessert;
import app.com.dessertCart.entity.DessertCart;
import app.com.dessertCart.entity.DessertCartDTO;
import app.com.dessertCart.repository.DessertCartRepository;
import app.com.dessertCart.service.DessertCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private DessertRepository dessertRepository;


    @Override
    public List<DessertCartDTO> getDessertCartByMemberId(Integer memberId) {
        List<DessertCart> dessertCartList = dessertCartRepository.findByMemberId(memberId);
        List<DessertCartDTO> dessertCartDTOList = new ArrayList<>();

        for (DessertCart dessertCart : dessertCartList) {
            DessertCartDTO dessertCartDTO = convertToDTO(dessertCart);
            dessertCartDTOList.add(dessertCartDTO);
        }
        return dessertCartDTOList;
    }

    private DessertCartDTO convertToDTO(DessertCart dessertCart) {
        Dessert dessert = dessertRepository.findById(dessertCart.getDessertId()).orElse(null);
        Integer cartDessertQuantity = dessertCart.getCartDessertQuantity();
        Integer subtotalAmount = null;
        String dessertName = null;
        Integer dessertPrice = null;

        if (dessert != null && cartDessertQuantity != null) {
            dessertName = dessert.getDessertName();
            dessertPrice = dessert.getDessertPrice();
            subtotalAmount = dessertPrice * cartDessertQuantity;
        }

        DessertCartDTO dessertCartDTO = new DessertCartDTO();
        dessertCartDTO.setDessertId(dessertCart.getDessertId());
        dessertCartDTO.setMemberId(dessertCart.getMemberId());
        dessertCartDTO.setCartDessertQuantity(dessertCart.getCartDessertQuantity());
        dessertCartDTO.setSubtotalAmount(subtotalAmount);
        dessertCartDTO.setDessertName(dessertName);
        dessertCartDTO.setDessertPrice(dessertPrice);

        return dessertCartDTO;
    }

    @Override
    public DessertCartDTO getDessertCartByDessertIdAndMemberId(Integer dessertId, Integer memberId) {
        Optional<DessertCart> optionalDessertCart = dessertCartRepository.findByDessertIdAndMemberId(dessertId, memberId);
        if (optionalDessertCart.isPresent()) {
            DessertCart dessertCart = optionalDessertCart.get();
            return convertToDTO(dessertCart);
        }
        return null;
    }


    @Override
    @Transactional
    public void updateDessertCartQuantity(Integer dessertId, Integer memberId, Integer cartDessertQuantity) {
        Optional<DessertCart> optionalDessertCart = dessertCartRepository.findByDessertIdAndMemberId(dessertId, memberId);
        DessertCart dessertCart = optionalDessertCart.orElse(null);

        if (dessertCart != null) {
            dessertCart.setCartDessertQuantity(cartDessertQuantity);
            dessertCartRepository.save(dessertCart);
        }
    }

    @Override
    @Transactional
    public void delete(Integer dessertId, Integer memberId) {
        dessertCartRepository.deleteByDessertIdAndMemberId(dessertId, memberId);
    }
}