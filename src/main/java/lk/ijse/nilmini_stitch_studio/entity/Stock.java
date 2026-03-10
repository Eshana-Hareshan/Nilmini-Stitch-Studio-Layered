package lk.ijse.nilmini_stitch_studio.entity;

import java.util.Date;

public class Stock {

    private int itemId;
    private String itemName;
    private int quantity;
    private double price;
    private long customerNumber;
    private String customerName;
    private Date dateAdded;
    private Date borrowDate;

    public Stock() {}

    public Stock(int itemId, String itemName, int quantity, double price, long customerNumber, String customerName, Date dateAdded, Date borrowDate) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.dateAdded = dateAdded;
        this.borrowDate = borrowDate;
    }

    public Stock(String itemName, int quantity, double price, long customerNumber, String customerName, Date dateAdded, Date borrowDate) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.dateAdded = dateAdded;
        this.borrowDate = borrowDate;
    }

    public int getItemId() {
        return itemId; }
    public void setItemId(int itemId) {
        this.itemId = itemId; }
    public String getItemName() {
        return itemName; }
    public void setItemName(String itemName) {
        this.itemName = itemName; }
    public int getQuantity() {
        return quantity; }
    public void setQuantity(int quantity) {
        this.quantity = quantity; }
    public double getPrice() {
        return price; }
    public void setPrice(double price) {
        this.price = price; }
    public long getCustomerNumber() {
        return customerNumber; }
    public void setCustomerNumber(long customerNumber) {
        this.customerNumber = customerNumber; }
    public String getCustomerName() {
        return customerName; }
    public void setCustomerName(String customerName) {
        this.customerName = customerName; }
    public Date getDateAdded() {
        return dateAdded; }
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded; }
    public Date getBorrowDate() {
        return borrowDate; }
    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate; }

    @Override
    public String toString() {
        return "StockDTO{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", customerNumber=" + customerNumber +
                ", customerName='" + customerName + '\'' +
                ", dateAdded=" + dateAdded +
                ", borrowDate=" + borrowDate +
                '}';
    }
}
