package app.com.dessertOrders.service;


import app.com.dessertOrders.entity.Orders;

import java.util.List;


/**
 * 業務層
 *
 * @author Charlie
 * @since 2023-06-21 10:35:15
 */
public interface OrdersService {

    List<Orders> getAllOrders();

    Orders getOrdersById(Integer ordersId);

    void update(Orders orders);


}

