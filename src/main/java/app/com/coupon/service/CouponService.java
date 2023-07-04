package app.com.coupon.service;

import app.com.coupon.repository.CouponTypeRepository;
import app.com.coupon.repository.MemberCpRepository;
import app.com.coupon.repository.MemberFollowRepository;
import app.com.coupon.vo.*;
import app.com.course.repository.CourseRepository;
import app.com.course.vo.Course;
import app.com.emp.repository.EmployeeRepository;
import app.com.emp.vo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CouponService {
    @Autowired
    private CouponTypeRepository repository;
    @Autowired
    private MemberCpRepository memberCpRepository;
    @Autowired
    private MemberFollowRepository memberFollowRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    //master管理cp頁面 顯示所有cp
    public List<CouponType> getAllCouponType() {
        List<CouponType> list = repository.findAll();
        return list;
    }

    //管理cp 新增cp到table
    public CouponType addCpType(CouponType coupon) {
        CouponType save = repository.save(coupon);
        return save;
    }

    //取得cpId號碼
    public CouponType getById(Integer cpid) {
        CouponType reference = repository.getReferenceById(cpid);
        return reference;
    }

    //編輯cp table專用
    public CouponType editCpType(CouponType coupon) {
        return repository.save(coupon);
    }


    //    課程頁面領取cp
    public List<CouponType> getCourseCp(Integer memberId) {
        List<CouponType> list = repository.findAllByCpTp(1);
        List<CouponType> result = new ArrayList<>();
        LocalDate now = LocalDate.now();

        Date dateNow = Date.valueOf(now);

        for (CouponType c :
                list) {
            Date cpStart = c.getCpStart();
            Date cpEnd = c.getCpEnd();
            if (!dateNow.before(cpStart) && dateNow.before(cpEnd)) {
                result.add(c);
            }
        }

        List<MembersCp> membersCpList = memberCpRepository.findAllByMemId(memberId);
        if (membersCpList != null) {
            for (MembersCp m :
                    membersCpList) {
                result.removeIf(c -> c.getCpId().equals(m.getCpId()));
            }
        }
        return result;
    }

    //新增會員&cp到table
    //CpUsed新增時 未使用狀態0
    public MembersCp addMemCp(MembersCpId memCp) {
        MembersCp membersCp = new MembersCp();
        membersCp.setMemId(memCp.getMemId());
        membersCp.setCpId(memCp.getCpId());
        membersCp.setCpUsed(0);
        return memberCpRepository.save(membersCp);
    }

    //甜點頁面領取cp
    public List<CouponType> getDessertCp(Integer memberId) {
        List<CouponType> list = repository.findAllByCpTp(0);
        List<CouponType> result = new ArrayList<>();
        LocalDate now = LocalDate.now();

        Date dateNow = Date.valueOf(now);

        for (CouponType c :
                list) {
            Date cpStart = c.getCpStart();
            Date cpEnd = c.getCpEnd();
            if (!dateNow.before(cpStart) && dateNow.before(cpEnd)) {
                result.add(c);
            }
        }

        List<MembersCp> membersCpList = memberCpRepository.findAllByMemId(memberId);
        if (membersCpList != null) {
            for (MembersCp m :
                    membersCpList) {
                System.out.println(m.getCpId());
                result.removeIf(c -> c.getCpId().equals(m.getCpId()));
            }
        }
        return result;
    }

    //顯示會員優惠券
    //會員優惠券
    public List<CouponType> getAllCouponTypeMem(Integer memberId) {
        List<MembersCp> list = memberCpRepository.findAllByMemId(memberId);
        List<CouponType> result = new ArrayList<>();
        for (MembersCp m :
                list) {
            CouponType reference = repository.getReferenceById(m.getCpId());
            result.add(reference);
        }
        return result;
    }

    public List<LoveCourse> getAllFollowCourse() {
        List<LoveCourse> list = memberFollowRepository.findAll();
        return list;
    }

    public List<LoveCourseMemDTO> findAllByMemberId(Integer memberId) {
        List<LoveCourse> loveCourses = memberFollowRepository.findAllByMemId(memberId);
        List<LoveCourseMemDTO> result = new ArrayList<>();

        for (LoveCourse l :
                loveCourses) {
            LoveCourseMemDTO dto = new LoveCourseMemDTO();
            Course course = courseRepository.getReferenceById(l.getCourseId());
            dto.setCourseContent(course.getCourseContent());
            dto.setCourseName(course.getCourseName());
            dto.setCoursePrice(course.getCoursePrice());
            dto.setCourseId(course.getCourseId());
            Employee employee = employeeRepository.getReferenceById(course.getEmpId());
            dto.setEmpName(employee.getEmpName());

            result.add(dto);
        }

        return result;
    }

    public void deleteFollowList(LoveCourse loveCourse) {
        memberFollowRepository.deleteById(loveCourse);
    }

    public List<ShoppingCpMemDTO> getAllMemCpDto(Integer memId) {
        List<MembersCp> allCpByMemId = memberCpRepository.findAllByMemId(memId);
        ArrayList<ShoppingCpMemDTO> result = new ArrayList<>();

        for (MembersCp m : allCpByMemId) {
            if (m.getCpUsed() == 0) {
                CouponType type = repository.getReferenceById(m.getCpId());
                if (type.getCpTp()==1){
                    ShoppingCpMemDTO dto = new ShoppingCpMemDTO();
                    dto.setCpName(type.getCpName());
                    dto.setCpDiscount(type.getCpDiscount());
                    dto.setCpThreshold(type.getCpThreshold());
                    dto.setCpId(type.getCpId());
                    result.add(dto);
                }
            }
        }
        result.forEach(System.out::println);
        return result;
    }

    public void addCourseFollow(Integer memberId, Integer courseId){
//        ArrayList<LoveCourse> result = new ArrayList<>();
        LoveCourse loveCourse = new LoveCourse();
        loveCourse.setCourseId(courseId);
        loveCourse.setMemId(memberId);
        memberFollowRepository.save(loveCourse);
    }
    public void updateCpStatus(Integer memberId, Integer cpId) {
        MembersCpId membersCpId = new MembersCpId();
        membersCpId.setCpId(cpId);
        membersCpId.setMemId(memberId);

        MembersCp data = memberCpRepository.getReferenceById(membersCpId);
        data.setCpUsed(1);

        memberCpRepository.save(data);
    }


    //追蹤清單
//    public List<CouponType> getAllCouponTypeMem(Integer memberId) {
//        List<MembersCp> list = memberCpRepository.findAllByMemId(memberId);
//        List<CouponType> result = new ArrayList<>();
//        for (MembersCp m:
//                list) {
//            CouponType reference = repository.getReferenceById(m.getCpId());
//            result.add(reference);
//        }
//        return result;
//    }

}
