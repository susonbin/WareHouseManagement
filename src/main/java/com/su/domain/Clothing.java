package com.su.domain;

import java.io.Serializable;

public class Clothing implements Serializable {
    private Integer id;
    private String barcode;
    private Integer slotId;
    private Slot slot;
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Clothing{" +
                "id=" + id +
                ", barcode='" + barcode + '\'' +
                ", slotId=" + slotId +
                ", slot=" + slot +
                ", quantity=" + quantity +
                '}';
    }
}
