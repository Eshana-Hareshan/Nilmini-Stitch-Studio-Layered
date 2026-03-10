package lk.ijse.nilmini_stitch_studio.dao.custom;

import lk.ijse.nilmini_stitch_studio.dao.CrudDAO;
import lk.ijse.nilmini_stitch_studio.dao.SuperDAO;
import lk.ijse.nilmini_stitch_studio.entity.OrderItem;
import lk.ijse.nilmini_stitch_studio.entity.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {

    List<Payment> customerPaymentDetails(String c_number)throws SQLException;


}
