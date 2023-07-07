package app.com.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component  // 給Spring IoC 託管
public class Main implements CommandLineRunner {// 這個class會在伺服器部屬完執行(main方法)

    @Override
    public void run(String... args){
        System.out.println("啟動成功");

    }
}
