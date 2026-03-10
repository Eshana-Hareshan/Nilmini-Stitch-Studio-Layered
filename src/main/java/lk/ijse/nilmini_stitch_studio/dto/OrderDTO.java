package lk.ijse.nilmini_stitch_studio.dto;

import java.util.Date;
import java.util.List;


public class OrderDTO {
    private int id;
    private int customerId;
    private Date date;
    private List <OrderItemDTO> orderItemList;

    public OrderDTO() {
    }

    public OrderDTO(int customerId, Date date, List<OrderItemDTO> orderItemList) {
        this.customerId = customerId;
        this.date = date;
        this.orderItemList = orderItemList;
    }

    public OrderDTO(int id, int customerId, Date date, List<OrderItemDTO> orderItemList) {
        this.id = id;
        this.customerId = customerId;
        this.date = date;
        this.orderItemList = orderItemList;
    }

    public OrderDTO(List<OrderItemDTO> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderItemDTO> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemDTO> orderItemList) {
        this.orderItemList = orderItemList;
    }
    
    
    
    
}
