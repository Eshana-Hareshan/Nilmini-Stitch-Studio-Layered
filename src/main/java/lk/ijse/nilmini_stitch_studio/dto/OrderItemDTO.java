/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.nilmini_stitch_studio.dto;

import java.util.List;

/**
 *
 * @author Admin
 */
public class OrderItemDTO {
    int itemId;
    int qty;
    double itemPrice;

    public OrderItemDTO() {
    }
    

    public OrderItemDTO(int itemId, int qty, double itemPrice) {
        this.itemId = itemId;
        this.qty = qty;
        this.itemPrice = itemPrice;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    
    
    
    
    
}
