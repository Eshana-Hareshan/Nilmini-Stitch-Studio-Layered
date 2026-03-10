package lk.ijse.nilmini_stitch_studio.dao.custom.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lk.ijse.nilmini_stitch_studio.dao.custom.DAOFactory;
import lk.ijse.nilmini_stitch_studio.dao.custom.OrderDAO;
import lk.ijse.nilmini_stitch_studio.dao.custom.OrderItemDAO;
import lk.ijse.nilmini_stitch_studio.db.DBConnection;
import lk.ijse.nilmini_stitch_studio.entity.Order;
import lk.ijse.nilmini_stitch_studio.entity.OrderItem;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


public class OrderDAOImpl implements OrderDAO {

    @Override
    public void print(int orderId) throws SQLException, JRException {
        
        Connection conn = DBConnection.getInstance().getConnection();
        
        InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/nilmini_stitch_studio/reports/invoice.jrxml");
        
        JasperReport jr = JasperCompileManager.compileReport(inputStream);
        
        Map<String, Object> params = new HashMap<>();
        params.put("ORDER_ID", orderId);
        
        JasperPrint jp = JasperFillManager.fillReport(jr, params, conn); 
        
        JasperViewer.viewReport(jp, false);
    }

    @Override
    public boolean save(Order customer) throws SQLException {
        return false;
    }

    @Override
    public boolean saveOrderItems(List<OrderItem> orderItemList, int orderId) throws SQLException {
        return false;
    }

    @Override
    public Order search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Order customer) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String customer) throws SQLException {
        return false;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public void print() throws SQLException, JRException {}

}
