package lk.ijse.nilmini_stitch_studio.bo.custom.impl;

import lk.ijse.nilmini_stitch_studio.bo.custom.CustomerBO;
import lk.ijse.nilmini_stitch_studio.dao.custom.CustomerDAO;
import lk.ijse.nilmini_stitch_studio.dao.custom.DAOFactory;
import lk.ijse.nilmini_stitch_studio.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.nilmini_stitch_studio.dto.CustomerDTO;
import lk.ijse.nilmini_stitch_studio.entity.Customer;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAOFactory(DAOFactory.DAOType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO cusDTO) throws SQLException {
        Customer customer = new Customer(cusDTO.getName(), cusDTO.getContactNumber(), cusDTO.getDate());
        return customerDAO.save(customer);
    }

    @Override
    public boolean updateCustomer(CustomerDTO cusDTO) throws SQLException {
        Customer customer = new Customer(cusDTO.getName(), cusDTO.getContactNumber());
        return customerDAO.update(customer);
    }

    @Override
    public boolean deleteCustomer(String contactNumber) throws SQLException {
        return customerDAO.delete(contactNumber);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws SQLException {

        List<Customer> customers = customerDAO.getAll();
        List<CustomerDTO> customerDTOs = new ArrayList<>();

        for(Customer customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO(customer.getName(), customer.getContactNumber(), customer.getDate());
            customerDTOs.add(customerDTO);
        }

        return customerDTOs;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException {
        Customer customer = customerDAO.search(id);

        return new CustomerDTO(customer.getName(), customer.getContactNumber(), customer.getDate());


    }

    @Override
    public void printCustomerReport() throws JRException, SQLException {
        customerDAO.print();
    }
}
