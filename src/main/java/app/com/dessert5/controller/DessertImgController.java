package app.com.dessert5.controller;

import app.com.dessert5.dao.DessertImageRepository;
import app.com.dessert5.dao.DessertRepository;
import app.com.dessert5.service.DessertImageService;
import app.com.dessert5.service.DessertService;
import app.com.dessert5.vo.Dessert;
import app.com.dessert5.vo.DessertImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RequestMapping("/dessert5")
@RestController
public class DessertImgController {

    @Autowired
    private DessertImageService dessertImageService;

    @Autowired
    private DessertRepository dessertRepository;

    @Autowired
    private DessertImageRepository dessertImageRepository;

    @PutMapping("/upload/{dessertId}")
    public String uploadImages(@PathVariable("dessertId") Integer dessertId,
                                               @RequestParam(name = "0", required = false) MultipartFile file1,
                                               @RequestParam(name = "1", required = false) MultipartFile file2,
                                               @RequestParam(name = "2", required = false) MultipartFile file3,
                                               @RequestParam(name = "3", required = false) MultipartFile file4) {
        System.out.println("file1: " + file1);
        System.out.println("file2: " + file2);
        System.out.println("file3: " + file3);
        System.out.println("file4: " + file4);

        return dessertImageService.updateDessertImage(dessertId, file1, file2, file3, file4);
    }

    @GetMapping("/image0")
    public byte[] Image0(@RequestParam Integer dessertId) throws IOException {
        byte[] image0 = dessertImageService.findImage0(dessertId);
        return image0;
    }

}
