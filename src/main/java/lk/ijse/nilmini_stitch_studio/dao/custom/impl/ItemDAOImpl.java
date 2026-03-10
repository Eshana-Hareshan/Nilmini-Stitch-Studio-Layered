package lk.ijse.nilmini_stitch_studio.dao.custom.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lk.ijse.nilmini_stitch_studio.dao.custom.ItemDAO;
import lk.ijse.nilmini_stitch_studio.db.DBConnection;
import lk.ijse.nilmini_stitch_studio.dto.ItemDTO;
import lk.ijse.nilmini_stitch_studio.entity.Customer;
import lk.ijse.nilmini_stitch_studio.entity.Item;
import lk.ijse.nilmini_stitch_studio.entity.OrderItem;
import lk.ijse.nilmini_stitch_studio.util.CrudUtil;
import net.sf.jasperreports.engine.JRException;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item item) throws SQLException {

        return CrudUtil.execute(
                "INSERT INTO item (fabric,qty,unit_price) VALUES (?,?,?)",
                item.getFabric(),
                item.getQty(),
                item.getPrice()
        );

    }

    @Override
    public boolean saveOrderItems(List<OrderItem> orderItemList, int orderId) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Item item) throws SQLException {

        return CrudUtil.execute(
                "UPDATE item SET fabric=?, qty=?, unit_price=? WHERE i_id=?",
                item.getFabric(),
                item.getQty(),
                item.getPrice(),
                item.getId()
        );

    }

    @Override
    public boolean delete(String id) throws SQLException {

        return CrudUtil.execute("DELETE FROM item WHERE i_id=?", id);

    }

    @Override
    public Item search(String id) throws SQLException {

        Connection conn = DBConnection.getInstance().getConnection();

        ResultSet rs = CrudUtil.execute(
                "SELECT * FROM item WHERE i_id=?",
                Integer.parseInt(id)
        );

        Item item = null;

        if (rs.next()) {

            int itemId = rs.getInt("i_id");
            String fabric = rs.getString("fabric");
            int qty = rs.getInt("qty");
            double price = rs.getDouble("unit_price");

            item = new Item(itemId, fabric, qty, price);

        }

        return item;

    }

    @Override
    public List<Item> getAll() throws SQLException {

        Connection conn = DBConnection.getInstance().getConnection();

        ResultSet rs = CrudUtil.execute("SELECT * FROM item");

        List<Item> itemList = new ArrayList<>();

        while (rs.next()) {

            int id = rs.getInt("i_id");
            String fabric = rs.getString("fabric");
            int qty = rs.getInt("qty");
            double price = rs.getDouble("unit_price");

            Item item = new Item(id, fabric, qty, price);

            itemList.add(item);

        }

        return itemList;

    }

    @Override
    public void print() throws SQLException, JRException {}

    @Override
    public boolean deceaseQTY(int itemId,int qty)throws SQLException {
        boolean result = CrudUtil.execute("UPDATE item SET qty=qty -? WHERE i_id = ? AND qty >=?",qty,itemId,qty);
        return result;
    }

}