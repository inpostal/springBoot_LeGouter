package app.com.news.controller;

import app.com.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//導至頁面
@Controller
public class NewsController {
    @Autowired
    private NewsService service;

    @GetMapping("/news/list")
    public String getNewsAll() {
        return "/back-end/news/newsManagement";
    }
////thyleaf
//    @GetMapping("/news/updateThyleaf")
//    public String updateNews(@RequestParam Integer newsId, Model model) {
//        //拿到id裡面資料
//        News news = service.getNewsById(newsId);
//        //將id裡面資料放入model
//        model.addAttribute("news", news);
//        //導至頁面
//        return "/back-end/news/updatenewManagement";
//    }
//
//    @PostMapping("/news/add")
//    //拿到前台資料
//    public ResponseEntity<?> update(@RequestParam("newsId") Integer newsId,
//                                    @RequestParam("empId") Integer empId,
//                                    @RequestParam("newsContent") String newsContent,
//                                    @RequestParam("newsPic") MultipartFile newsPic,
//                                    @RequestParam("newsTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newsTime,
//                                    @RequestParam("newsTitle") String newsTitle) {
//       //拿到後台資料
//        News news = service.getNewsById(newsId);
//        //將前台資料放入後台
//        news.setEmpId(empId);
//        news.setNewsContent(newsContent);
//        news.setNewsTime(Date.valueOf(newsTime));
//        news.setNewsTitle(newsTitle);
//        //將圖片轉成byte
//        try {
//            news.setNewsPic(newsPic.getBytes());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        //將資料放入資料庫(save)==>有pk就update沒有就insert
//        service.update(news);
//
//        return ResponseEntity.ok().build();
//    }
//
//
//    @GetMapping("/news/img/{newsId}")
//    //拿到圖片
//    public ResponseEntity<?>getNewsImageById(@PathVariable Integer newsId) {
//        News news=service.getNewsById(newsId);
//        byte[] image=news.getNewsPic();
//        ByteArrayResource resource=new ByteArrayResource(image);
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_GIF)
//                .body(resource);
//    }
//
//
//    //新增:將資料送到資料庫
//    @GetMapping("/news/add")
//    //拿到前台資料
//    public ResponseEntity<?> addNews(@RequestParam("empId") Integer empId,
//                                     @RequestParam("newsContent") String newsContent,
//                                     @RequestParam("newsPic") MultipartFile newsPic,
//                                     @RequestParam("newsTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newsTime,
//                                     @RequestParam("newsTitle") String newsTitle) {
//        //建立一個物件
//        News news = new News();
//        //將前台資料放入後台
//        news.setEmpId(empId);
//        news.setNewsContent(newsContent);
//        news.setNewsTime(Date.valueOf(newsTime));
//        news.setNewsTitle(newsTitle);
//        try {
//            news.setNewsPic(newsPic.getBytes());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        service.add(news);
//        return ResponseEntity.ok().build();
//    }


}