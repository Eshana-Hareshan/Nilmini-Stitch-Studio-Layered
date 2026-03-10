package lk.ijse.nilmini_stitch_studio.entity;

import java.util.Date;
import java.util.List;

public class Order {

        private int id;
        private int customerId;
        private Date date;
        private List<OrderItem> orderItemList;

        public Order() {
        }

        public Order(int customerId, Date date, List<OrderItem> orderItemList) {
            this.customerId = customerId;
            this.date = date;
            this.orderItemList = orderItemList;
        }

        public Order(int id, int customerId, Date date, List<OrderItem> orderItemList) {
            this.id = id;
            this.customerId = customerId;
            this.date = date;
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

        public List<OrderItem> getOrderItemList() {
            return orderItemList;
        }

        public void setOrderItemList(List<OrderItem> orderItemList) {
            this.orderItemList = orderItemList;
        }






}
