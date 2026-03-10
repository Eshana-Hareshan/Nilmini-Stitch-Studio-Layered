package lk.ijse.nilmini_stitch_studio.entity;

public class Item {

    private int id;
    private String fabric;
    private int qty;
    private double price;

    public Item() {
    }

    public Item(int id, String fabric, int qty, double price) {
        this.id = id;
        this.fabric = fabric;
        this.qty = qty;
        this.price = price;
    }

    public Item(String fabric, int qty, double price) {
        this.fabric = fabric;
        this.qty = qty;
        this.price = price;
    }

    public Item(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }


    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
