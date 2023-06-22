package app.com.dessert5.service;

import app.com.dessert5.dao.DessertImageRepository;
import app.com.dessert5.dao.DessertRepository;
import app.com.dessert5.vo.Dessert;
import app.com.dessert5.vo.DessertImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
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
                // 找到對應商品Dessert
                Dessert dessert = dessertRepository.findById(dessertId).orElse(null);

                // 找到原本存的對應的圖片
                List<DessertImage> dessertImageList = dessertRepository.findByDessertId(dessertId);

                MultipartFile[] files = {file1, file2, file3, file4};

                for (int i = 0; i < files.length; i++) {
                        if (files[i] != null) {
                                // 新建一個DessertImage物件
                                DessertImage dessertImage = new DessertImage();
                                try {
                                        // 把圖片存入新建的DessertImage物件
                                        dessertImage.setDessertImage(files[i].getBytes());
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                                // 更新DessertImage物件的dessert屬性，讓他可以找到對應的Dessert
                                dessertImage.setDessert(dessert);
                                dessertImageRepository.save(dessertImage);
                        } else {
                                if (dessertImageList.size() > i) {
                                        dessertImageRepository.delete(dessertImageList.get(i));
                                }
                        }
                }











                return "上傳成功";

        }

}
