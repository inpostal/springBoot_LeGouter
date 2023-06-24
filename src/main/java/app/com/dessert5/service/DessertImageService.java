package app.com.dessert5.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface DessertImageService {

    String updateDessertImage(Integer dessertId,
                              MultipartFile file1,
                              MultipartFile file2,
                              MultipartFile file3,
                              MultipartFile file4);

    byte[] findImage0(Integer dessertId);

    byte[] findImage1(Integer dessertId);

    byte[] findImage2(Integer dessertId);

    byte[] findImage3(Integer dessertId);

    String deleteImage0(Integer dessertId);

    String deleteImage1(Integer dessertId);

    String deleteImage2(Integer dessertId);

    String deleteImage3(Integer dessertId);
}
