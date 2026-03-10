package lk.ijse.nilmini_stitch_studio.dto;

import java.util.Date;

public class PaymentDTO {
    private long cNumber;
    private String cName;
    private String fabric;
    private Date date;
    private double price;

    public PaymentDTO() {
    }

    public PaymentDTO(String fabric, Date date, double price) {
        this.fabric = fabric;
        this.date = date;
        this.price = price;
    }

    public PaymentDTO(String cName, String fabric, Date date, double price) {
        this.cName = cName;
        this.fabric = fabric;
        this.date = date;
        this.price = price;
    }

    public long getcNumber() {
        return cNumber;
    }

    public void setcNumber(long cNumber) {
        this.cNumber = cNumber;
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    
    
    
}
