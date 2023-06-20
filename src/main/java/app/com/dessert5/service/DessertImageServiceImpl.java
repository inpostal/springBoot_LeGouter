package app.com.dessert5.service;

import app.com.dessert5.dao.DessertImageRepository;
import app.com.dessert5.dao.DessertRepository;
import app.com.dessert5.vo.DessertImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class DessertImageServiceImpl implements DessertImageService {

        @Autowired
        private DessertRepository dessertRepository;

        @Autowired
        private DessertImageRepository dessertImageRepository;

        public String updateDessertImage(Integer dessertId,
                                         MultipartFile file1,
                                         MultipartFile file2,
                                         MultipartFile file3,
                                         MultipartFile file4) {
                // 找到對應商品
                // Dessert dessert = dessertRepository.findById(dessertId).orElse(null);

                // 找到原本存的對應的圖片
                List<DessertImage> dessertImages = dessertImageRepository.findByDessert_DessertId(dessertId);

                MultipartFile[] files = {file1, file2, file3, file4};

                // 如果有上傳圖片，就更新圖片
                for (int i = 0; i < files.length; i++) {
                        if (!files[i].isEmpty()) {
                                DessertImage dessertImage = dessertImages.get(i); // 拿出原本的圖片
                                try {
                                        dessertImage.setDessertImg(files[i].getBytes());
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                                dessertImageRepository.save(dessertImage);
                        }
                }


                return "上傳成功";

        }

}
