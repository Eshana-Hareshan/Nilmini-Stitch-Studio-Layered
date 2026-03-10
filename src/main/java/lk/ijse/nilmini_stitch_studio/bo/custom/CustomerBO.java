package lk.ijse.nilmini_stitch_studio.bo.custom;

import lk.ijse.nilmini_stitch_studio.bo.SuperBO;
import lk.ijse.nilmini_stitch_studio.dto.CustomerDTO;
import lk.ijse.nilmini_stitch_studio.entity.Customer;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {

    public boolean saveCustomer(CustomerDTO cusDTO) throws SQLException ;

    public boolean updateCustomer(CustomerDTO cusDTO) throws SQLException ;

    public boolean deleteCustomer(String number) throws SQLException ;

    public List<CustomerDTO> getAllCustomers() throws SQLException ;

    public CustomerDTO searchCustomer(String id) throws SQLException ;

    public void printCustomerReport() throws JRException, SQLException ;
}
