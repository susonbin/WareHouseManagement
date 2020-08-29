package com.su.service.impl;

import com.su.dao.ShelfDao;
import com.su.domain.Shelf;
import com.su.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("shelfService")
public class ShelfServiceImpl implements ShelfService {

    @Autowired
    private ShelfDao shelfDao;

    @Override
    public void initTable() {

    }

    @Override
    public void expandShelf() {

    }

    @Override
    public void removeShelfById(Integer id) {

    }

    @Override
    public Shelf findShelfByName(Character character) {
        System.out.println("service:"+shelfDao.findShelfByName(character));
        return shelfDao.findShelfByName(character);
    }
}
