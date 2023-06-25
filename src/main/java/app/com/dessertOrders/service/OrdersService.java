package app.com.dessertOrders.service;


import app.com.dessertOrders.entity.Orders;
import app.com.dessertOrders.entity.OrdersDTO;

import java.util.List;


/**
 * 業務層
 *
 * @author Charlie
 * @since 2023-06-21 10:35:15
 */
public interface OrdersService {
    List<OrdersDTO> getAllOrdersDTO();

    OrdersDTO getOrdersDTOById(Integer ordersId);

    void updateOrders(Integer ordersId, String receiverName, String receiverAddress, String receiverPhone,
                      Integer orderStatus, String receiverEmail);

    Orders getOrdersById(Integer ordersId);
}


