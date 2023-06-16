package app.com.main;

import app.com.emp.repository.EmployeeRepository;
import app.com.member.repository.MemberRepository;
import app.com.emp.vo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component  // 給Spring IoC 託管
public class Main implements CommandLineRunner {// 這個class會在伺服器部屬完執行(main方法)

    @Autowired   // 注入studentRepository實例 (dao)
    private MemberRepository memberRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args){
        List<Employee> all = employeeRepository.findAll();

        for (Employee e:
             all) {
            System.out.println(e);
        }

//        System.out.println("啟動成功");
    }
}
