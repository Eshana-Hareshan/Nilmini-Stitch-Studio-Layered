package lk.ijse.nilmini_stitch_studio.dto;

/**
 *
 * @author Admin
 */
public class OrderItemTM {
    private int itemId;
    private String fabric;
    private double price;
    private int qty;
    private double totalPrice;

    public OrderItemTM() {
    }

    public OrderItemTM(int id, String fabric, double price, int qty, double totalPrice) {
        this.itemId = id;
        this.fabric = fabric;
        this.price = price;
        this.qty = qty;
        this.totalPrice = totalPrice;
    }

    public OrderItemTM(int id,String fabric, String button, String thread, String ilastic, String men, String women, double price, int qty, double totalPrice) {
        this.itemId = id;
        this.fabric = fabric;
        this.price = price;
        this.qty = qty;
        this.totalPrice = totalPrice;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int id) {
        this.itemId = id;
    }

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    

    
    
    
    
}
