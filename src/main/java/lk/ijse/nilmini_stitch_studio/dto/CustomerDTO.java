package lk.ijse.nilmini_stitch_studio.dto;

import java.util.Date;


public class CustomerDTO {
    private String name;
    private long contactNumber;
    private Date date;

    public CustomerDTO() {
    }

    public CustomerDTO(String name, long contactNumber, Date date) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.date = date;
    }

    public CustomerDTO( String name, long contactNumber) {
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