package app.com.dessertOrders.service.impl;


import app.com.dessertOrders.entity.Orders;
import app.com.dessertOrders.repository.OrdersRepository;
import app.com.dessertOrders.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Orders> getAllOrders() {
        List<Orders> all = ordersRepository.findAll();
        return all;
    }

    @Override
    public Orders getOrdersById(Integer ordersId) {
        Optional<Orders> orders = ordersRepository.findById(ordersId);
        return orders.get();
    }

    @Override
    public void update(Orders orders) {
        ordersRepository.save(orders);
    }


}

