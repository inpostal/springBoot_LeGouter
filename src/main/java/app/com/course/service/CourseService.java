package app.com.course.service;

import app.com.coupon.repository.CouponTypeRepository;
import app.com.coupon.vo.CouponType;
import app.com.course.repository.ChefRepository;
import app.com.course.repository.CourseOrderRepository2;
import app.com.course.repository.CourseRepository;
import app.com.course.vo.*;
import app.com.emp.repository.EmployeeRepository;
import app.com.emp.vo.Employee;
import app.com.member.repository.MemberRepository;
import app.com.member.vo.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//找人不找方法, 都叫Service
@Service
public class CourseService {

    @Autowired
    CourseRepository repository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CourseOrderRepository2 courseOrderRepository2;

    @Autowired
    ChefRepository chefRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CouponTypeRepository cpRepository;

    public List<Course> getCourse() {
        return repository.findAll();
    }


    public Course getCourseById(Integer courseId) {
        Optional<Course> course = repository.findById(courseId);
        return course.get();
    }


    public Course addCourse(Course course) {
        return repository.save(course);
    }

    public void delete(Integer courseId) {
        repository.deleteById(courseId);
    }

    public void update(Course course) {
        repository.save(course);
    }


    //儲存結帳資料
    public CourseOrder2 checkout(CheckoutDTO dto) {
        CourseOrder2 courseOrder = new CourseOrder2();
        courseOrder.setMemId(dto.getMemberId());
        courseOrder.setOrderTotal(dto.getCoursePrice());
        courseOrder.setCourseId(dto.getCourseId());
        if (dto.getCpId() != 0) {
            courseOrder.setCpId(dto.getCpId());
            CouponType couponType = cpRepository.getReferenceById(dto.getCpId());
            courseOrder.setCpOrderTotal(dto.getCoursePrice() - couponType.getCpDiscount());
        }
        System.out.println(courseOrder);
        return courseOrderRepository2.save(courseOrder);
    }

    //SHOW結帳資料
    public CheckoutDTO getCheckoutData(Integer memberId, Integer courseId) {
        CheckoutDTO dto = new CheckoutDTO();

        // 從資料庫中根據 memberId 查找會員
        Members member = memberRepository.findById(memberId).orElse(null);
        if (member != null) {
            dto.setMemberId(member.getMemberId());
            dto.setMemberName(member.getMemberName());
            dto.setMemberEmail(member.getMemberEmail());
            dto.setMemberPhone(member.getMemberPhone());
        }

        // 從資料庫中根據 courseId 查找課程
        Course course = repository.findById(courseId).orElse(null);
        if (course != null) {
            dto.setCourseName(course.getCourseName());
            dto.setCoursePrice(course.getCoursePrice());
        }

        return dto;
    }


    public ChefInfoDTO getChefData(Integer empId,
                                   Integer chefId) {
        ChefInfoDTO dto = new ChefInfoDTO();

        Employee employee = employeeRepository.findById(empId).orElse(null);
        if (employee != null) {
            dto.setEmpId(employee.getEmpId());
            dto.setEmpName(employee.getEmpName());
            dto.setEmpClassify(employee.getEmpClassify());
            dto.setEmpAccount(employee.getEmpAccount());
            dto.setEmpPhone(employee.getEmpPhone());
            dto.setEmpHireDate(employee.getEmpHireDate());
            dto.setEmpMail(employee.getEmpMail());
        }

        Chef chef = chefRepository.findById(chefId).orElse(null);
        if (chef != null) {
            dto.setChefId(chef.getChefId());
            dto.setChefInfo(chef.getChefInfo());
        }
        return dto;
    }

    public Chef updateChefInfo(Integer chefId, String chefInfo) {
        Chef chef = chefRepository.findById(chefId).orElse(null);
        if (chef != null) {
            chef.setChefInfo(chefInfo);
            chefRepository.save(chef);
        }
        return chef;
    }

    public boolean isCoursePurchased(Integer courseId, Integer memId) {
        return courseOrderRepository2.existsByCourseIdAndMemId(courseId, memId);
    }

}

