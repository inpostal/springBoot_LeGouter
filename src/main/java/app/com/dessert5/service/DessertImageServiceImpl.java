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

        @Autowired
        private DessertImageRepository dessertImageRepository;


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
                if(dessert.getDessertImageMap().get(0) == null){
                        return null;}
                return dessert.getDessertImageMap().get(0).getDessertImage();
        }

        @Override
        public byte[] findImage1(Integer dessertId) {
                Dessert dessert = dessertRepository.findById(dessertId).orElse(null);
                if(dessert.getDessertImageMap().get(1) == null){
                        return null;}
                return dessert.getDessertImageMap().get(1).getDessertImage();
        }

        @Override
        public byte[] findImage2(Integer dessertId) {
                Dessert dessert = dessertRepository.findById(dessertId).orElse(null);
                if(dessert.getDessertImageMap().get(2) == null){
                        return null;}
                return dessert.getDessertImageMap().get(2).getDessertImage();
        }

        @Override
        public byte[] findImage3(Integer dessertId) {
                Dessert dessert = dessertRepository.findById(dessertId).orElse(null);
                if(dessert.getDessertImageMap().get(3) == null){
                        return null;}
                return dessert.getDessertImageMap().get(3).getDessertImage();
        }

        @Override
        public String deleteImage0(Integer dessertId) {
                Dessert dessert = dessertRepository.findById(dessertId).orElse(null);
                dessertImageRepository.deleteByDessertIdAndDessertImageMapKey(dessertId, 0);
                dessert.getDessertImageMap().put(0, null);
                dessertRepository.save(dessert);

                return "刪除成功";
        }

        @Override
        public String deleteImage1(Integer dessertId) {
                Dessert dessert = dessertRepository.findById(dessertId).orElse(null);
                dessertImageRepository.deleteByDessertIdAndDessertImageMapKey(dessertId, 1);
                dessert.getDessertImageMap().put(1, null);
                dessertRepository.save(dessert);

                return "刪除成功";
        }

        @Override
        public String deleteImage2(Integer dessertId) {
                Dessert dessert = dessertRepository.findById(dessertId).orElse(null);
                dessertImageRepository.deleteByDessertIdAndDessertImageMapKey(dessertId, 2);
                dessert.getDessertImageMap().put(2, null);
                dessertRepository.save(dessert);

                return "刪除成功";
        }

        @Override
        public String deleteImage3(Integer dessertId) {
                Dessert dessert = dessertRepository.findById(dessertId).orElse(null);
                dessertImageRepository.deleteByDessertIdAndDessertImageMapKey(dessertId, 3);
                dessert.getDessertImageMap().put(3, null);
                dessertRepository.save(dessert);

                return "刪除成功";
        }
}
