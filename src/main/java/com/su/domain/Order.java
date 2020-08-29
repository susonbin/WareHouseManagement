package com.su.domain;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private Integer id;
    private String name;
    private String itemsJson;
    private Integer state;

    private static class Item {

        private String clothing;
        private String quantity;
        private Boolean isPick;

        public String getClothing() {
            return clothing;
        }

        public void setClothing(String clothing) {
            this.clothing = clothing;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public Boolean getIsPick() {
            return isPick;
        }

        public void setIsPick(Boolean isPick) {
            this.isPick = isPick;
        }

        @Override
        public String toString() {
            return "ClothesQuantity{" +
                    "clothing='" + clothing + '\'' +
                    ", quantity='" + quantity + '\'' +
                    ", isPick='" + isPick + '\'' +
                    '}';
        }
    };
    private List<Item> items;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemsJson() {
        return itemsJson;
    }

    public void setItemsJson(String itemsJson) {
        this.itemsJson = itemsJson;
    }

    public void itemsToJsonString(){
        state=2;
        for(Item item : items){
            if(item.isPick==false)state=1;
        }
        itemsJson = JSON.toJSONString(items);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        if(items ==null){
            items = JSON.parseArray(itemsJson, Item.class);
            System.out.println(items);
        }
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", num=" + name +
                ", itemsJson='" + itemsJson + '\'' +
                ", state=" + state +
                ", items=" + items +
                '}';
    }
}
