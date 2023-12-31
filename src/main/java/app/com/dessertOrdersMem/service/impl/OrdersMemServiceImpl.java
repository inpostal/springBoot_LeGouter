package app.com.dessertOrdersMem.service.impl;


import app.com.dessertOrderDetail.entity.OrderDetailDTO;
import app.com.dessertOrderDetail.service.OrderDetailService;
import app.com.dessertOrdersMem.entity.OrdersMem;
import app.com.dessertOrdersMem.repository.OrdersMemRepository;
import app.com.dessertOrdersMem.service.OrdersMemService;
import app.com.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 業務層
 *
 * @author Charlie
 * @since 2023-06-21 10:35:16
 */
@Service
public class OrdersMemServiceImpl implements OrdersMemService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrdersMemRepository ordersMemRepository;

    @Autowired
    private OrderDetailService orderDetailService;

    public List<OrdersMem> getAllOrdersByMemberId(Integer memId) {
        return ordersMemRepository.findByMemId(memId);
    }

    public String getMemberAccountById(Integer memId) {
        return memberRepository.findById(memId).get().getMemberName();
    }

    public Map<Integer, List<OrderDetailDTO>> getDessertDetails(List<OrdersMem> orders) {
        Map<Integer, List<OrderDetailDTO>> dessertDetailsMap = new HashMap<>();
        for (OrdersMem order : orders) {
            List<OrderDetailDTO> dessertDetails = orderDetailService.getOrderDetailDTOList(order.getOrderId());
            dessertDetailsMap.put(order.getOrderId(), dessertDetails);
        }
        return dessertDetailsMap;
    }

    public List<OrderDetailDTO> getDessertDetails(Integer orderId) {
        return orderDetailService.getOrderDetailDTOList(orderId);
    }

    public List<OrdersMem> getAllOrdersByMemberIdOrderByOrderTimeDesc(Integer memId) {
        return ordersMemRepository.findByMemIdOrderByOrderTimeDesc(memId);
    }

}