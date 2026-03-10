package lk.ijse.nilmini_stitch_studio.entity;

public class OrderItem {


    int itemId;
    int qty;
    double itemPrice;

    public OrderItem() {
    }


    public OrderItem(int itemId, int qty, double itemPrice) {
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
