package app.com.dessertOrders.service.impl;

import app.com.dessertOrders.entity.Orders;
import app.com.dessertOrders.entity.OrdersDTO;
import app.com.dessertOrders.repository.OrdersRepository;
import app.com.dessertOrders.service.OrdersService;
import app.com.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * 業務層
 *
 * @author Charlie
 * @since 2023-06-21 10:35:16
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private MemberService memberService;

    @Override
    public Orders getOrdersById(Integer ordersId) {
        Optional<Orders> orders = ordersRepository.findById(ordersId);
        return orders.get();
    }


    @Override
    public void updateOrders(Integer ordersId, String receiverName, String receiverAddress, String receiverPhone,
                             Integer orderStatus, String receiverEmail) {
        Orders orders = ordersRepository.findById(ordersId).orElse(null);
        if (orders != null) {
            orders.setReceiverName(receiverName);
            orders.setReceiverAddress(receiverAddress);
            orders.setReceiverPhone(receiverPhone);
            orders.setOrderStatus(orderStatus);
            orders.setReceiverEmail(receiverEmail);
            ordersRepository.save(orders);
        }
    }

    @Override
    public List<OrdersDTO> getAllOrdersDTOSortedById() {
        List<Orders> list = ordersRepository.findAllByOrderByOrderIdDesc();
        List<OrdersDTO> dtoList = new ArrayList<>();

        for (Orders o : list) {
            OrdersDTO dto = new OrdersDTO();
            dto.setOrderId(o.getOrderId());
            dto.setMemberAC(memberService.getMemberById(o.getMemId()).getMemberAccount());
            dto.setOrdersDate(o.getOrderTime());
            dto.setCpOrderTotal(o.getCpOrderTotal());
            dto.setOrderStatus(o.getOrderStatus());
            dtoList.add(dto);
        }

        return dtoList;
    }

}