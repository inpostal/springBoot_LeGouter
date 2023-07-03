package app.com.grouporderdetail.repository;

import app.com.grouporderdetail.vo.GroupOrderDetail;
import app.com.grouporderdetail.vo.GroupOrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupOrderDetailRepository extends JpaRepository<GroupOrderDetail, GroupOrderDetailId> {
    List<GroupOrderDetail> findByGroupOrderId(Integer groupOrderId);
    List<GroupOrderDetail> findAllByMemberId(Integer memberId);
    Long countByGroupOrderId(Integer groupOrderId);
    Long countByGroupOrderIdAndGroupProductStatus(Integer groupOrderId, byte groupProductStatus);
}
