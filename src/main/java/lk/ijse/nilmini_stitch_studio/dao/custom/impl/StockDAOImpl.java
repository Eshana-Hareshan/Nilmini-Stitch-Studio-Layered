package lk.ijse.nilmini_stitch_studio.dao.custom.impl;

import lk.ijse.nilmini_stitch_studio.dao.custom.StockDAO;
import lk.ijse.nilmini_stitch_studio.db.DBConnection;
import lk.ijse.nilmini_stitch_studio.entity.OrderItem;
import lk.ijse.nilmini_stitch_studio.entity.Stock;
import lk.ijse.nilmini_stitch_studio.util.CrudUtil;
import net.sf.jasperreports.engine.JRException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDAOImpl implements StockDAO {

    public boolean save(Stock stock) throws SQLException {

        return
        CrudUtil.execute("INSERT INTO stock(item_name, quantity, price, c_number, c_name, date_added, borrow_date) VALUES (?, ?, ?, ?, ?, ?, ?)",
                stock.getItemName(),stock.getQuantity(),stock.getPrice(),
                stock.getCustomerNumber(),stock.getCustomerName(),stock.getDateAdded(),stock.getBorrowDate()
        );
    }

    @Override
    public boolean saveOrderItems(List<OrderItem> orderItemList, int orderId) throws SQLException {
        return false;
    }

    @Override
    public Stock search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Stock stock) throws SQLException {

        return CrudUtil.execute("UPDATE stock SET item_name=?, quantity=?, price=?, c_number=?, c_name=?, date_added=?, borrow_date=? WHERE item_id=?;",
                stock.getItemName(),stock.getQuantity(),stock.getPrice(), stock.getCustomerNumber(),stock.getCustomerName(),(Date) stock.getDateAdded(),(Date) stock.getBorrowDate());
    }

    @Override
    public List<Stock> getAll() throws SQLException {

        ResultSet rs = CrudUtil.execute("SELECT * FROM stock");

        List<Stock> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Stock(
                    rs.getInt("item_id"),
                    rs.getString("item_name"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getLong("c_number"),
                    rs.getString("c_name"),
                    rs.getDate("date_added"),
                    rs.getDate("borrow_date")
            ));
        }
        return list;
    }

    @Override
    public void print() throws SQLException, JRException {}


    @Override
    public boolean delete(String itemId) throws SQLException {

        return CrudUtil.execute("DELETE FROM stock WHERE item_id=?", itemId);
}

}
