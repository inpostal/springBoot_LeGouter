package app.com.emp.repository;

import app.com.emp.vo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByEmpAccountAndEmpPassword(String empAccount, String empPassword);

    Employee findByEmpAccount(String empAccount);

    Employee findByEmpMail(String empMail);
}
