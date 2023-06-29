package app.com.dessertOrderDetail.service.impl;

import app.com.dessert5.dao.DessertImageRepository;
import app.com.dessert5.dao.DessertRepository;
import app.com.dessert5.vo.Dessert;
import app.com.dessertOrderDetail.entity.OrderDetail;
import app.com.dessertOrderDetail.entity.OrderDetailDTO;
import app.com.dessertOrderDetail.repository.OrderDetailRepository;
import app.com.dessertOrderDetail.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: OrderDetailImpl
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/24 AM 12:28
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private DessertRepository dessertRepository;
    @Autowired
    private DessertImageRepository dessertImageRepository;


    @Override
    public List<OrderDetailDTO> getOrderDetailDTOList(Integer orderId) {
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();

        for (OrderDetail orderDetail : orderDetails) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setOrderId(orderDetail.getOrderId());
            orderDetailDTO.setDessertId(orderDetail.getDessertId());
            orderDetailDTO.setDessertPrice(orderDetail.getDessertPrice());
            orderDetailDTO.setDessertAmount(orderDetail.getDessertAmount());

            Dessert dessert = dessertRepository.getReferenceById(orderDetail.getDessertId());
            orderDetailDTO.setDessertName(dessert.getDessertName());


            orderDetailDTOList.add(orderDetailDTO);
        }

        return orderDetailDTOList;
    }
}
