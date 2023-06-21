package app.com.dessertC.service;


import app.com.dessertC.entity.Orders;

import java.util.List;


/**
 * 业务层
 *
 * @author makejava
 * @since 2023-06-21 10:35:15
 */
public interface OrdersService {

    List<Orders> getAllOrders();

    Orders getOrdersById(Integer ordersId);

    void update(Orders orders);


}

