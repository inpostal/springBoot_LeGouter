package app.com.dessertOrderDetail.service;

import app.com.dessertOrderDetail.entity.OrderDetailDTO;

import java.util.List;

/**
 * ClassName: OrderDetailService
 * Description:
 *
 * @Author Charlie
 * @Create 2023/6/24 AM 12:20
 */
public interface OrderDetailService {

    List<OrderDetailDTO> getOrderDetailDTOList(Integer orderId);
}
