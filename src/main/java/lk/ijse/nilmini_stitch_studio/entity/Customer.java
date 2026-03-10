package lk.ijse.nilmini_stitch_studio.entity;

import java.util.Date;

public class Customer {

    private String name;
    private long contactNumber;
    private Date date;

    public Customer() {
    }

    public Customer(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Customer(String name, long contactNumber, Date date) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.date = date;
    }

    public Customer( String name, long contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" + ", name=" + name + ", contactNumber=" + contactNumber + ", date=" + date + '}';
    }
}
