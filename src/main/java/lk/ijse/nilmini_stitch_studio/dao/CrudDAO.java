package lk.ijse.nilmini_stitch_studio.dao;

import lk.ijse.nilmini_stitch_studio.entity.OrderItem;
import net.sf.jasperreports.engine.*;

import java.sql.*;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {

    public boolean save(T customer) throws SQLException ;

    public boolean saveOrderItems(List<OrderItem> orderItemList, int orderId) throws SQLException;

    public T search(String id) throws SQLException ;

    public boolean update(T customer) throws SQLException ;

    public boolean delete(String customer) throws SQLException ;

    public List<T> getAll() throws SQLException ;


    public void print() throws SQLException, JRException ;



}
