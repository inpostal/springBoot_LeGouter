package app.com.dessertOrdersMem.service.impl;


import app.com.dessertOrdersMem.entity.OrdersMem;
import app.com.dessertOrdersMem.repository.OrdersMemRepository;
import app.com.dessertOrdersMem.service.OrdersMemService;
import app.com.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public List<OrdersMem> getAllOrdersByMemberId(Integer memId) {
        return ordersMemRepository.findByMemId(memId);
    }

    public String getMemberAccountById(Integer memId) {
        return memberRepository.findById(memId).get().getMemberAccount();
    }
}