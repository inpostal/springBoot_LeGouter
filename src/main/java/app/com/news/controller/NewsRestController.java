package app.com.news.controller;

import app.com.emp.vo.Employee;
import app.com.news.service.NewsService;
import app.com.news.vo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class NewsRestController {
    @Autowired
    private NewsService service;

    @GetMapping("/check/emp/login")
    public Employee getEmp(HttpSession session){
        Employee emp = (Employee) session.getAttribute("emp");
        System.out.println(emp);
        return emp;
    }

//取得所有值,直接返回數據
    @GetMapping("/news/getAll")
    public List<News>getAll(){
        List<News> list = service.getAllNews();
        return list;
    }

}
