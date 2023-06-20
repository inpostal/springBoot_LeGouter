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

}
