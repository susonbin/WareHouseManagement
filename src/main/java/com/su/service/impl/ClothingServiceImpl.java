package com.su.service.impl;

import com.su.dao.ClothingDao;
import com.su.domain.Clothing;
import com.su.service.ClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clothingService")
public class ClothingServiceImpl implements ClothingService {

    @Autowired
    private ClothingDao clothingDao;

    @Override
    public void initTable() {

    }

    @Override
    public void addClothing(Clothing clothing) {
        System.out.println(clothing);
        clothingDao.addClothing(clothing);
    }

    @Override
    public void removeClothingById(Integer id) {
        clothingDao.removeClothingById(id);
    }

    @Override
    public void updateClothing(Clothing clothing) {
        clothingDao.updateClothing(clothing);
    }

    @Override
    public List<Clothing> findClothesByBarcode(String barcode) {
        System.out.println("%"+barcode+"%");
        System.out.println(clothingDao.findClothesByBarcode("%"+barcode+"%"));
        return clothingDao.findClothesByBarcode("%"+barcode+"%");
    }

    @Override
    public Clothing findClothingByBarcode(String barcode) {
        return clothingDao.findClothingByBarcode(barcode);
    }

    @Override
    public Clothing findClothingById(Integer id) {
        return null;
    }
}
