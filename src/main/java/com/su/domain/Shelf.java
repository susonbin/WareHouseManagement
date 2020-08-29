package com.su.domain;

import java.io.Serializable;
import java.util.List;

public class Shelf implements Serializable {

    private Integer id;
    private Character name;
    private List<Slot> slots;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getName() {
        return name;
    }

    public void setName(Character name) {
        this.name = name;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "id=" + id +
                ", name=" + name +
                ", slots=" + slots +
                '}';
    }
}
