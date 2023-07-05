package app.com.dessertCart.service.impl;

import app.com.dessert5.dao.DessertRepository;
import app.com.dessert5.vo.Dessert;
import app.com.dessertCart.entity.DessertCart;
import app.com.dessertCart.entity.DessertCartDTO;
import app.com.dessertCart.entity.OrderInfo;
import app.com.dessertCart.repository.DessertCartRepository;
import app.com.dessertCart.service.DessertCartService;
import app.com.dessertOrderDetail.entity.OrderDetail;
import app.com.dessertOrderDetail.repository.OrderDetailRepository;
import app.com.dessertOrders.entity.Orders;
import app.com.dessertOrders.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;


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

    @Override
    @Transactional
    public String submitOrder(Integer memberId, OrderInfo orderInfo) {
        List<DessertCart> dessertCartList = dessertCartRepository.findByMemberId(memberId);

        // 創建訂單
        Orders order = new Orders();
        order.setMemId(memberId); // 設置會員ID
        int orderTotal = 0;

        // 設置訂單詳情
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (DessertCart dessertCart : dessertCartList) {
            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setDessertId(dessertCart.getDessertId());
            orderDetail.setDessertAmount(dessertCart.getCartDessertQuantity());

            // 根據dessertId查詢甜點價格
            Dessert dessert = dessertRepository.findById(dessertCart.getDessertId()).orElse(null);
            if (dessert != null) {
                orderDetail.setDessertPrice(dessert.getDessertPrice());
                orderTotal += dessert.getDessertPrice() * dessertCart.getCartDessertQuantity();
            }

            orderDetails.add(orderDetail);
        }
        order.setOrderTotal(orderTotal);
        int shippingCost = orderTotal > 500 ? 0 : 100; // Recalculate shipping cost
        order.setCpOrderTotal(orderTotal + shippingCost - 75);


        // 設置收件人資訊，根據您提供的HTML代碼
        order.setReceiverPhone(orderInfo.getReceiverPhone());
        order.setReceiverAddress(orderInfo.getReceiverAddress());
        order.setReceiverName(orderInfo.getReceiverName());
        order.setReceiverEmail(orderInfo.getReceiverEmail());
        order.setOrderTime(new Timestamp(System.currentTimeMillis()));


        ordersRepository.save(order);
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrderId(order.getOrderId()); // 設置訂單ID
        }
        orderDetailRepository.saveAll(orderDetails);

        // 清空購物車
        dessertCartRepository.deleteAll(dessertCartList);


        return order.getOrderId().toString();


    }


}