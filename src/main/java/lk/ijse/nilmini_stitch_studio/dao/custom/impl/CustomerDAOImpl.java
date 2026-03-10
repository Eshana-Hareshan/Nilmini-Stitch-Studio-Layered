package lk.ijse.nilmini_stitch_studio.dao.custom.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ijse.nilmini_stitch_studio.dao.custom.CustomerDAO;
import lk.ijse.nilmini_stitch_studio.db.DBConnection;
import lk.ijse.nilmini_stitch_studio.entity.Customer;
import lk.ijse.nilmini_stitch_studio.entity.OrderItem;
import lk.ijse.nilmini_stitch_studio.util.CrudUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer customer) throws SQLException {
        
        boolean result = CrudUtil.execute("INSERT INTO customer (c_name,c_number,reg_date) VALUES (?,?,?)",customer.getName(),customer.getContactNumber(),new Date());
        return result;
    }

    @Override
    public boolean saveOrderItems(List<OrderItem> orderItemList, int orderId) throws SQLException {
        return false;
    }

    @Override
    public Customer search(String id) throws SQLException {
    
        Connection conn = DBConnection.getInstance().getConnection();
                
        String sql = "SELECT * FROM customer WHERE c_number=?";
                
        PreparedStatement ptsm = conn.prepareStatement(sql);
        ptsm.setInt(1, Integer.parseInt(id));
                
        ResultSet rs = ptsm.executeQuery();

        Customer customer = null;

        if(rs.next()) {
            String cusName = rs.getString("c_name");
            long cusNumber = rs.getInt("c_number");
            Date cusDate = rs.getDate("reg_date");
            
            customer = new Customer(cusName,cusNumber,cusDate);
        }
        
        return customer;
        
    }

    @Override
    public boolean update(Customer customer) throws SQLException  {
            
            return CrudUtil.execute("UPDATE customer SET c_name=?  WHERE c_number=?",customer.getName(),customer.getContactNumber());
    }

    @Override
    public boolean delete(String contactNumber) throws SQLException {

            return CrudUtil.execute("DELETE FROM customer WHERE c_number=?",contactNumber);
    }

    @Override
    public List<Customer> getAll() throws SQLException {
    
        Connection conn = DBConnection.getInstance().getConnection();
        
        String sql = "SELECT * FROM customer";
        
        PreparedStatement ptsm = conn.prepareStatement(sql);
        
        ResultSet rs = ptsm.executeQuery();
        
        List<Customer> customerList = new ArrayList<>();
        
        while(rs.next()) {
            String name = rs.getString("c_name");
            long number = rs.getLong("c_number");
            Date date = rs.getDate("reg_date");
            
            Customer customer = new Customer(name,number,date);
            customerList.add(customer);
        }
        
        return customerList;
    }

    @Override
    public void print() throws SQLException, JRException {
        
        Connection conn =DBConnection.getInstance().getConnection();
        InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/nilmini_stitch_studio/reports/customer_rpt.jrxml");
        
        JasperReport jr = JasperCompileManager.compileReport(inputStream);
        
        JasperPrint jp =JasperFillManager.fillReport(jr,null,conn);
        
        JasperViewer.viewReport(jp);
        
    }
    
    
}
