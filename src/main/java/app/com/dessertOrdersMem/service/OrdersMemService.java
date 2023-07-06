package app.com.dessertOrdersMem.service;


import app.com.dessertOrderDetail.entity.OrderDetailDTO;
import app.com.dessertOrdersMem.entity.OrdersMem;

import java.util.List;


/**
 * 業務層
 *
 * @author Charlie
 * @since 2023-06-21 10:35:15
 */
public interface OrdersMemService {
    List<OrdersMem> getAllOrdersByMemberId(Integer memId);

    String getMemberAccountById(Integer memId);

    List<OrderDetailDTO> getDessertDetails(Integer orderId);

    List<OrdersMem> getAllOrdersByMemberIdOrderByOrderTimeDesc(Integer memId);

}


