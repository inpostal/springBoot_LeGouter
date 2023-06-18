package app.com.emp.service;

import app.com.emp.repository.EmployeeRepository;
import app.com.emp.vo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public Employee checkLogin(String empAccount, String empPassword) {
        return repository.findByEmpAccountAndEmpPassword(empAccount, empPassword);
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee addEmp(Employee emp) {
        Employee employee = repository.save(emp);
        return employee;
    }

    public boolean checkAccount(String empAccount) {
        Employee employee = repository.findByEmpAccount(empAccount);
        if (employee != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkMail(String empMail) {
        Employee empAccount = repository.findByEmpMail(empMail);
        if (empAccount != null) {
            return true;
        } else {
            return false;
        }
    }

    public Employee getEmp(Integer empId) {
        return repository.findById(empId).get();
    }

    public void updateEmp(Employee employee) {
        repository.save(employee);
    }
}
