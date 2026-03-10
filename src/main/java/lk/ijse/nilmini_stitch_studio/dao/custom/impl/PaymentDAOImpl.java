package lk.ijse.nilmini_stitch_studio.dao.custom.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ijse.nilmini_stitch_studio.dao.custom.PaymentDAO;
import lk.ijse.nilmini_stitch_studio.entity.OrderItem;
import lk.ijse.nilmini_stitch_studio.entity.Payment;
import lk.ijse.nilmini_stitch_studio.util.CrudUtil;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Admin
 */
public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public List<Payment> customerPaymentDetails(String c_number)throws SQLException{

        ResultSet rs = CrudUtil.execute(
                "SELECT c.c_name,i.fabric,o.o_date,oi.price FROM customer c JOIN orders o ON c.c_number = o.c_number" +
                        " JOIN order_item oi ON o.o_id = oi.o_id JOIN item i  ON oi.i_id = i.i_id WHERE c.c_number = ?;",c_number);

        List<Payment> objList = new ArrayList<>();


        while(rs.next()){
            String cName=rs.getString("c_name");
            String fabric=rs.getString("fabric");
            Date date=rs.getDate("o_date");
            double price = rs.getDouble("price");
            
            Payment payment = new Payment(cName,fabric,date,price);
            objList.add(payment);
        }
        
         return objList;        
        
        
    }

    @Override
    public boolean saveOrderItems(List<OrderItem> orderItemList,int orderId) throws SQLException {
        
        double total = 0.0;
        
        for (OrderItem orderItem : orderItemList) {
            double qtyPrice=(orderItem.getItemPrice())*(double)(orderItem.getQty());
            total+=qtyPrice;
        }        
        
        boolean result = CrudUtil.execute("INSERT INTO payment (o_id,total_price) VALUES (?,?)",
                orderId,
                total
                 
        );
        if (!result){
            throw new SQLException();
        }
        return true;
    }

    @Override
    public boolean save(Payment customer) throws SQLException {
        return false;
    }

    @Override
    public Payment search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Payment customer) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String customer) throws SQLException {
        return false;
    }

    @Override
    public List<Payment> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public void print() throws SQLException, JRException {

    }
}
