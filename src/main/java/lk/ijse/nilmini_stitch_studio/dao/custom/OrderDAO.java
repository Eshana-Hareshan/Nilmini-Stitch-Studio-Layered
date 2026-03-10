package lk.ijse.nilmini_stitch_studio.dao.custom;

import lk.ijse.nilmini_stitch_studio.dao.CrudDAO;
import lk.ijse.nilmini_stitch_studio.dao.SuperDAO;
import lk.ijse.nilmini_stitch_studio.entity.Order;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
    void print(int orderId) throws SQLException, JRException;
}
