package lk.ijse.nilmini_stitch_studio.dao.custom.impl;

import java.sql.SQLException;
import java.util.List;

import lk.ijse.nilmini_stitch_studio.dao.custom.DAOFactory;
import lk.ijse.nilmini_stitch_studio.dao.custom.ItemDAO;
import lk.ijse.nilmini_stitch_studio.dao.custom.OrderItemDAO;
import lk.ijse.nilmini_stitch_studio.dao.custom.PaymentDAO;
import lk.ijse.nilmini_stitch_studio.entity.OrderItem;
import lk.ijse.nilmini_stitch_studio.util.CrudUtil;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Admin
 */
public class OrderItemDAOImpl implements OrderItemDAO {
    
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAOFactory(DAOFactory.DAOType.ITEM);
    PaymentDAO paymentDAO = (PaymentDAO)  DAOFactory.getInstance().getDAOFactory(DAOFactory.DAOType.PAYMENT);

    @Override
    public boolean saveOrderItems(List<OrderItem> orderItemList, int orderId)throws SQLException {

        for (OrderItem orderItem : orderItemList ){
            
            double qtyPrice=(orderItem.getItemPrice())*(double)(orderItem.getQty());
            
            boolean result = CrudUtil.execute("INSERT INTO order_item (o_id,i_id,qty,price) VALUES (?,?,?,?)",
                    orderId,
                    orderItem.getItemId(),
                    orderItem.getQty(),
                    orderItem.getItemPrice()
            );
            
            if (result){
                boolean result1= itemDAO.deceaseQTY(orderItem.getItemId(), orderItem.getQty());
                
                if (!result1){
                    throw new SQLException();
                }
            } else {
                throw new SQLException();
            }
            
        }
        boolean result= paymentDAO.saveOrderItems(orderItemList,orderId);

        return true;
    }


    @Override
    public boolean save(OrderItem customer) throws SQLException {
        return false;
    }

    @Override
    public OrderItem search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(OrderItem customer) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String customer) throws SQLException {
        return false;
    }

    @Override
    public List<OrderItem> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public void print() throws SQLException, JRException {

    }
}
