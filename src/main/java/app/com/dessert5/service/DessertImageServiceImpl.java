package app.com.dessert5.service;

import app.com.dessert5.dao.DessertImageRepository;
import app.com.dessert5.dao.DessertRepository;
import app.com.dessert5.vo.Dessert;
import app.com.dessert5.vo.DessertImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DessertImageServiceImpl implements DessertImageService {

        @Autowired
        private DessertRepository dessertRepository;


        public String updateDessertImage(Integer dessertId,
                                         MultipartFile file1,
                                         MultipartFile file2,
                                         MultipartFile file3,
                                         MultipartFile file4) {
                // 找到對應商品Dessert
                Dessert dessert = dessertRepository.findById(dessertId).orElse(null);

                // 取得原本存的對應Dessert的圖片陣列
                Map<Integer, DessertImage> dessertImageMap = dessert.getDessertImageMap();


                MultipartFile[] files = {file1, file2, file3, file4};



                for (int i = 0; i < files.length; i++) {
                        System.out.println(files.length);
                        if (files[i] != null) {
                                // 新建一個DessertImage物件
                                DessertImage dessertImage = new DessertImage();

                                // 設定要存入的dessertImage物件的各屬性
                                try {
                                        // 把圖片存入新建的DessertImage物件
                                        dessertImage.setDessertImage(files[i].getBytes());

                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                                dessertImage.setDessert(dessert);

                                // 把新建的DessertImage物件加入對應的Dessert的圖片集合
                                dessertImageMap.put(i, dessertImage);
                        }
                }

                dessertRepository.save(dessert);

                return "上傳成功";

        }

        @Override
        public byte[] findImage0(Integer dessertId) {
                Dessert dessert = dessertRepository.findById(dessertId).orElse(null);
                return dessert.getDessertImageMap().get(0).getDessertImage();
        }
}
