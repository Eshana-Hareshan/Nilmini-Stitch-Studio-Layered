package lk.ijse.nilmini_stitch_studio.bo.custom.impl;

import lk.ijse.nilmini_stitch_studio.bo.custom.OrderBO;
import lk.ijse.nilmini_stitch_studio.dao.custom.DAOFactory;
import lk.ijse.nilmini_stitch_studio.dao.custom.OrderDAO;
import lk.ijse.nilmini_stitch_studio.dao.custom.OrderItemDAO;
import lk.ijse.nilmini_stitch_studio.dao.custom.impl.OrderDAOImpl;
import lk.ijse.nilmini_stitch_studio.dao.custom.impl.OrderItemDAOImpl;
import lk.ijse.nilmini_stitch_studio.db.DBConnection;
import lk.ijse.nilmini_stitch_studio.dto.ItemDTO;
import lk.ijse.nilmini_stitch_studio.dto.OrderDTO;
import lk.ijse.nilmini_stitch_studio.dto.OrderItemDTO;
import lk.ijse.nilmini_stitch_studio.entity.Order;
import lk.ijse.nilmini_stitch_studio.entity.OrderItem;
import lk.ijse.nilmini_stitch_studio.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAOFactory(DAOFactory.DAOType.ORDER);
    OrderItemDAO orderItemDAO = (OrderItemDAO) DAOFactory.getInstance().getDAOFactory(DAOFactory.DAOType.ORDERITEM);


    public int placeOrder(OrderDTO orderDTO) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();

        try{
            int orderId = 0;

            conn.setAutoCommit(false);

            boolean result = CrudUtil.execute("INSERT INTO orders (o_date,c_number) VALUES(?,?)",orderDTO.getDate(),orderDTO.getCustomerId());

            if (result){
                ResultSet rs= CrudUtil.execute("SELECT o_id FROM orders ORDER BY o_id DESC LIMIT 1");

                if (rs.next()){
                    orderId = rs.getInt("o_id");
//                    boolean result2= orderItemDAO.saveOrderItems(orderDTO.getOrderItemList(), orderId);

                    List<OrderItem> orderItemList = new ArrayList<>();

                    for (OrderItemDTO orderItemDTO : orderDTO.getOrderItemList()){
                        OrderItem orderItem= new OrderItem(orderItemDTO.getItemId(),orderItemDTO.getQty(),orderItemDTO.getItemPrice());
                        orderItemList.add(orderItem);
                    }

                    boolean result2 = orderItemDAO.saveOrderItems(orderItemList, orderId);


                }
            }else {
                throw new SQLException();
            }
            conn.commit();

            return orderId;

        } catch(Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public void print(int result) {
    }
}
