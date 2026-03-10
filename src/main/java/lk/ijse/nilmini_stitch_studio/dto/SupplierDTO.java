/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.nilmini_stitch_studio.dto;

import java.util.Date;

public class SupplierDTO {
    private int id;
    private String name;
    private String catogory;
    private double price;
    private long sNumber;
    private Date date;

    public SupplierDTO() {}

    public SupplierDTO(String name, String catogory, double price, long sNumber, Date date) {
        this.name = name;
        this.catogory = catogory;
        this.price = price;
        this.sNumber = sNumber;
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCatogory() { return catogory; }
    public void setCatogory(String catogory) { this.catogory = catogory; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public long getSNumber() { return sNumber; }
    public void setSNumber(long sNumber) { this.sNumber = sNumber; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    @Override
    public String toString() {
        return "SupplierDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", catogory='" + catogory + '\'' +
                ", price=" + price +
                ", sNumber=" + sNumber +
                ", date=" + date +
                '}';
    }
}