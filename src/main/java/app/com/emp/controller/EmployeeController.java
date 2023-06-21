package app.com.emp.controller;

import app.com.emp.service.EmployeeService;
import app.com.emp.vo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/employee/data/edit/{empId}")
    public String editEmp(@PathVariable Integer empId, Model model) {
        Employee employee = service.getEmp(empId);
        model.addAttribute("employee", employee);
        return "back-end/Employee/EmpDataEdit";
    }


    @GetMapping("/employee/get/img/{empId}")
    public ResponseEntity<Resource> getPicture(@PathVariable Integer empId) {
        Employee employee = service.getEmp(empId);
        byte[] picture = employee.getEmpPicture();

        ByteArrayResource resource = new ByteArrayResource(picture);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_GIF) // or another appropriate media type
                .body(resource);
    }

    @PostMapping("/employee/update")
    @ResponseBody
    public ResponseEntity<?> updateEmployee(
            @RequestParam("empId") Integer empId,
            @RequestParam("empName") String empName,
            @RequestParam("empPhone") String empPhone,
            @RequestParam("empMail") String empMail,
            @RequestParam("empPassword") String empPassword,
            @RequestParam("empClassify") Integer empClassify,
            @RequestParam("empStatus") Integer empStatus,
            @RequestParam("empHireDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate empHireDate,
            @RequestParam(value = "photo", required = false) MultipartFile photo) {

        Employee employee = service.getEmp(empId);

        employee.setEmpName(empName);
        employee.setEmpPhone(empPhone);
        employee.setEmpMail(empMail);
        employee.setEmpPassword(empPassword);
        employee.setEmpClassify(empClassify);
        employee.setEmpHireDate(java.sql.Date.valueOf(empHireDate));
        employee.setEmpStatus(empStatus);

        if (photo != null && !photo.isEmpty()) {
            try {
                employee.setEmpPicture(photo.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        service.updateEmp(employee);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/employee/addemp")
    @ResponseBody
    public ResponseEntity<?> addEmployee(
            @RequestParam("empName") String empName,
            @RequestParam("empPhone") String empPhone,
            @RequestParam("empAccount") String empAccount,
            @RequestParam("empMail") String empMail,
            @RequestParam("empPassword") String empPassword,
            @RequestParam("empClassify") Integer empClassify,
            @RequestParam("empHireDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate empHireDate,
            @RequestParam("photo") MultipartFile photo) {

        Employee employee = new Employee();
        employee.setEmpName(empName);
        employee.setEmpPhone(empPhone);
        employee.setEmpAccount(empAccount);
        employee.setEmpMail(empMail);
        employee.setEmpPassword(empPassword);
        employee.setEmpClassify(empClassify);
        employee.setEmpHireDate(java.sql.Date.valueOf(empHireDate));
        employee.setEmpStatus(1);

        try {
            employee.setEmpPicture(photo.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, return an error response, etc.
        }

        service.addEmp(employee);

        return ResponseEntity.ok().build(); // or return an appropriate response
    }

    @PostMapping("/employee/checkAccount")
    @ResponseBody
    public Map<String, Boolean> checkAccount(@RequestParam String empAccount) {
        if (service.checkAccount(empAccount)) {
            return Map.of("result", true);
        } else {
            return Map.of("result", false);
        }
    }

    @PostMapping("/employee/checkMail")
    @ResponseBody
    public Map<String, Boolean> checkMail(@RequestParam String empMail) {
        if (service.checkMail(empMail)) {
            return Map.of("result", true);
        } else {
            return Map.of("result", false);
        }
    }


    // 新增員工網址
    @GetMapping("/employee/add")
    public String add(HttpSession session) {
        if (session.getAttribute("emp") != null) {
            return "/back-end/Employee/EmpDataInsert";
        } else {
            return "redirect:/employee/login";
        }
    }

    // 員工資料列表網址
    @GetMapping("/employee/manage")
    public String manage(HttpSession session, Model model) {
        if (session.getAttribute("emp") != null) {
            model.addAttribute("empList", service.findAll());
            return "/back-end/Employee/EmpDataList";
        } else {
            return "redirect:/employee/login";
        }
    }

    // 員工登入網址
    @GetMapping("/employee/login")
    public String login() {
        return "/back-end/Employee/EmpLogin";
    }

    // 員工資料網址
    @GetMapping("/employee/data")
    public String employeeData(HttpSession session) {
        if (session.getAttribute("emp") != null) {
            return "/back-end/Employee/EmpData";
        } else {
            return "redirect:/employee/login";
        }
    }

    // 員工登出網址
    @GetMapping("/employee/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/employee/login";
    }

    @PostMapping("/employee/login/check")
    @ResponseBody
    public Map<String, Boolean> checkLogin(@RequestBody Employee emp, HttpSession session) {
        Employee employee = service.checkLogin(emp.getEmpAccount(), emp.getEmpPassword());
        if (employee != null) {
            session.setAttribute("emp", employee);
            return Map.of("result", true);
        }else {
            return Map.of("result", false);
        }
    }
}
