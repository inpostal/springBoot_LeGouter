package app.com.news.controller;

import app.com.news.service.NewsService;
import app.com.news.vo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

//導至頁面
@Controller
public class NewsController {
    @Autowired
    private NewsService service;

    @GetMapping("/news/list")
    public String getNewsAll() {
        return "/back-end/news/newsManagement";
    }

    public List<News> getNewaAllFront(){
        return service.getAllNews();
    }



    //刪除

    @GetMapping("/news/delete")
    public String deleteNews(@RequestParam Integer newsId) {
        service.delete(newsId);
        // 導至頁面
        return "redirect:/back-end/news/newsManagement";
    }

    //thyleaf
    @GetMapping("/news/updateThyleaf")
    public String updateNews(@RequestParam Integer newsId, Model model) {
        //拿到id裡面資料
        News news = service.getNewsById(newsId);
        //將id裡面資料放入model
        model.addAttribute("news", news);
        //導至頁面
        return "/back-end/news/updatenewManagement";
    }

    @PostMapping("/news/update")
    //拿到前台資料
    public ResponseEntity<?> update(@RequestParam("newsId") Integer newsId,
                                    @RequestParam("empId") Integer empId,
                                    @RequestParam("newsContent") String newsContent,
                                    @RequestParam("newsPic") MultipartFile newsPic,
                                    @RequestParam("newsTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newsTime,
                                    @RequestParam("newsTitle") String newsTitle) {
        //拿到後台資料
        News news = service.getNewsById(newsId);
        //將前台資料放入後台
        news.setEmpId(empId);
        news.setNewsContent(newsContent);
        news.setNewsTime(Date.valueOf(newsTime));
        news.setNewsTitle(newsTitle);
        //將圖片轉成byte
        try {
            news.setNewsPic(newsPic.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //將資料放入資料庫(save)==>有pk就update沒有就insert
        service.update(news);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/news/img/{newsId}")
    //拿到圖片
    public ResponseEntity<?> getNewsImageById(@PathVariable Integer newsId) {
        News news = service.getNewsById(newsId);
        byte[] image = news.getNewsPic();
        ByteArrayResource resource = new ByteArrayResource(image);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_GIF)
                .body(resource);
    }


    @GetMapping("/news/addnews")
    public String addnews() {
        return "/back-end/news/addNewsManagement";
    }


    //新增:將資料送到資料庫
@PostMapping("/news/add")
    public ResponseEntity<?> addNews(
            //拿到錢端傳來的資料
            @RequestParam("empId") Integer empId,
            @RequestParam("newsContent") String newsContent,
            @RequestParam("newsPic") MultipartFile newsPic,
//            @RequestParam("newsTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newsTime,
            @RequestParam("newsTitle") String newsTitle) {
        //建立news物件放入所有傳入的值
        News news = new News();
        news.setEmpId(empId);
        news.setNewsContent(newsContent);
//        news.setNewsTime(Date.valueOf(newsTime));
        news.setNewsTitle(newsTitle);
        //照片用資料流傳入
        try {
            news.setNewsPic(newsPic.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //將news物件放入資料庫
        service.add(news);
        return ResponseEntity.ok().build();
    }
    //前台所有最新消息
    @GetMapping("/news/front")
public String getNewsFront(Model model){
        List<News> newList=service.getAllNewsFront();
        model.addAttribute("newList",newList);
        return "/front-end/news/news";
}
//前台最新消息單一內容
@GetMapping("/news/frontSingle")
public String getNewFrontSingle(@RequestParam Integer newsId, Model model){
        News getNewsFrontSingle=service.getNewsSingleFront(newsId);
        model.addAttribute("getNewsFrontSingle",getNewsFrontSingle);
        return "/front-end/news/newsSingle";
}



}