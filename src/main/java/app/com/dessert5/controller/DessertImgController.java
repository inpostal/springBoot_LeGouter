package app.com.dessert5.controller;

import app.com.dessert5.dao.DessertImageRepository;
import app.com.dessert5.dao.DessertRepository;
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
    private DessertRepository dessertRepository;

    @Autowired
    private DessertImageRepository dessertImageRepository;

    @PostMapping("/upload/{dessertId}")
    public ResponseEntity<String> uploadImages(@PathVariable("dessertId") Integer dessertId,
                                               @RequestParam("images") MultipartFile[] images) {
        try {
            Dessert dessert = dessertRepository.findById(dessertId).orElse(null);
            if (dessert != null) {
                for (MultipartFile image : images) {
                    DessertImage dessertImage = new DessertImage();
                    dessertImage.setDessertImg(image.getBytes());
                    dessertImage.setDessert(dessert);
                    dessertImageRepository.save(dessertImage);
                }
                return ResponseEntity.ok("Images uploaded successfully.");

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dessert not found.");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading images.");
        }
    }

}
