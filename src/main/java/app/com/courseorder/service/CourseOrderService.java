package app.com.courseorder.service;


import app.com.coupon.repository.CouponTypeRepository;
import app.com.course.repository.CourseRepository;
import app.com.course.vo.Course;
import app.com.courseorder.repository.CourseOrderRepository;
import app.com.courseorder.vo.CourseOrder;
import app.com.courseorder.vo.CourseOrderDTO;
import app.com.member.repository.MemberRepository;
import app.com.member.vo.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseOrderService {
    @Autowired
    private CourseOrderRepository courseOrderRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CouponTypeRepository cpRepository;

    public List<CourseOrderDTO> getCourseOrderList(){
        List<CourseOrderDTO> result = new ArrayList<>();

        List<CourseOrder> courseOrders = courseOrderRepository.findAll();

        for (CourseOrder c:
             courseOrders) {
            CourseOrderDTO dto = new CourseOrderDTO();
            Members members = memberRepository.getReferenceById(c.getMemId());
            Course course = courseRepository.getReferenceById(c.getCourseId());

            dto.setCourseOrderId(c.getCourseOrderId());
            dto.setCourseOrderPrice(course.getCoursePrice());
            dto.setMemberName(members.getMemberName());
            dto.setCourseDate(c.getOrderTime());
            dto.setCourseName(course.getCourseName());

            result.add(dto);
        }

        return result;
    }

    public List<CourseOrderDTO> getMemberCourseOrderList(Integer memberId){
        List<CourseOrder> list = courseOrderRepository.findAllByMemId(memberId);

        List<CourseOrderDTO> result = new ArrayList<>();

        for (CourseOrder c:
             list) {
            CourseOrderDTO dto = new CourseOrderDTO();
            Course course = courseRepository.getReferenceById(c.getCourseId());
            dto.setCourseId(c.getCourseId());
            dto.setCourseOrderId(c.getCourseOrderId());
            dto.setCourseOrderPrice(course.getCoursePrice());
            dto.setCourseName(course.getCourseName());
            dto.setCourseDate(c.getOrderTime());
            if (c.getCpId()!=null){
                dto.setCpName(cpRepository.getReferenceById(c.getCpId()).getCpName());
                dto.setCourseOrderPrice(c.getCpOrderTotal());
            }
            result.add(dto);
        }
        return  result;
    }
}
