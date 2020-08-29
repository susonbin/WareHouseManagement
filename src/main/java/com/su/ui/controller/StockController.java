package com.su.ui.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.su.domain.Clothing;
import com.su.domain.Shelf;
import com.su.domain.Slot;
import com.su.service.ClothingService;
import com.su.service.ShelfService;
import com.su.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private ClothingService clothingService;
    @Autowired
    private ShelfService shelfService;
    @Autowired
    private SlotService slotService;

    @RequestMapping("/findClothesByBarcode")
    public @ResponseBody List<Clothing> findClothesByBarcode(@RequestBody String json){
        System.out.println(json);
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject.get("barcode"));
        String barcode=(String) jsonObject.get("barcode");
        return clothingService.findClothesByBarcode(barcode);
    }

    @RequestMapping("/findClothingByBarcode")
    public @ResponseBody Clothing findClothingByBarcode(@RequestBody String json){
        System.out.println("看这里");
        System.out.println(json);
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject.get("barcode"));
        String barcode=(String) jsonObject.get("barcode");
        return clothingService.findClothingByBarcode(barcode);
    }

    @RequestMapping("/removeClothingById")
    public void removeClothingById(Integer id){
        System.out.println(id);
        clothingService.removeClothingById(id);
    }

    @RequestMapping("/updateClothing")
    public void updateClothing(@RequestBody Clothing clothing){
        System.out.println(clothing);
        //System.out.println(clothing);
        clothingService.updateClothing(clothing);
    }

    @RequestMapping("/findClothingByShelfAndSlot")
    public @ResponseBody Clothing findClothingByShelfAndSlot(@RequestBody String json){
        System.out.println(json);
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject.get("shelf"));
        System.out.println(jsonObject.get("slot"));
        Character shelfName=((String) jsonObject.get("shelf")).charAt(0);
        Integer slotName=Integer.valueOf((String) jsonObject.get("slot"));
        System.out.println(shelfName);
        System.out.println(slotName);
        Shelf shelf=shelfService.findShelfByName(shelfName);
        System.out.println(shelf);
        for(Slot slot: shelf.getSlots()){
            if(slot.getName()==slotName){
                System.out.println(slot);
                return slot.getClothing();
            }
        }
        return null;
    }

    @RequestMapping("/findSlotIdByShelfAndSlot")
    public @ResponseBody Integer findSlotIdByShelfAndSlot(@RequestBody String json){
        System.out.println(json);
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject.get("shelf"));
        System.out.println(jsonObject.get("slot"));
        Character shelfName=((String) jsonObject.get("shelf")).charAt(0);
        Integer slotName=Integer.valueOf((String) jsonObject.get("slot"));
        System.out.println(shelfName);
        System.out.println(slotName);
        Shelf shelf=shelfService.findShelfByName(shelfName);
        System.out.println(shelf);
        for(Slot slot: shelf.getSlots()){
            if(slot.getName()==slotName){
                System.out.println(slot);
                return slot.getId();
            }
        }
        return null;
    }

    @RequestMapping("/addClothing")
    public void addClothing(@RequestBody Clothing clothing){
        System.out.println(clothing);
        clothingService.addClothing(clothing);
    }
}
