package lk.ijse.nilmini_stitch_studio.dao.custom.impl;

import lk.ijse.nilmini_stitch_studio.dao.custom.PaymentDAO;
import lk.ijse.nilmini_stitch_studio.dao.custom.SupplierDAO;
import lk.ijse.nilmini_stitch_studio.db.DBConnection;
import lk.ijse.nilmini_stitch_studio.dto.SupplierDTO;
import lk.ijse.nilmini_stitch_studio.entity.OrderItem;
import lk.ijse.nilmini_stitch_studio.entity.Supplier;
import lk.ijse.nilmini_stitch_studio.util.CrudUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean save(Supplier suppiler) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO supplier (s_name, catogory, price, s_number, reg_date) VALUES (?,?,?,?,?)",
                suppiler.getName(), suppiler.getCatogory(), suppiler.getPrice(), suppiler.getSNumber(),suppiler.getDate());
    }

    @Override
    public boolean saveOrderItems(List<OrderItem> orderItemList, int orderId) throws SQLException {
        return false;
    }

    @Override
    public Supplier search(String id) throws SQLException {
        return null;
    }

    public boolean update(Supplier suppiler) throws SQLException {
        return CrudUtil.execute(
                "UPDATE supplier SET s_name=?, catogory=?, price=? WHERE s_number=?",
                suppiler.getName(), suppiler.getCatogory(), suppiler.getPrice(), suppiler.getSNumber());
    }

    public boolean delete(String sNumber) throws SQLException {
        return CrudUtil.execute("DELETE FROM supplier WHERE s_number=?", Integer.parseInt(sNumber));
    }

    public List<Supplier> getAll() throws SQLException {

        ResultSet rs = CrudUtil.execute("SELECT * FROM supplier");

        List<Supplier> supplierList = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("s_name");
            String catogory = rs.getString("catogory");
            double price = rs.getDouble("price");
            long number = rs.getLong("s_number");
            Date date = rs.getDate("reg_date");

            supplierList.add(new Supplier(name, catogory, price, number, date));
        }
        return supplierList;
    }

    @Override
    public void print() throws SQLException, JRException {
        Connection conn = DBConnection.getInstance().getConnection();
        InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/nilmini_stitch_studio/reports/supplier_rpt.jrxml");
        JasperReport jr = JasperCompileManager.compileReport(inputStream);
        JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
        JasperViewer.viewReport(jp);
    }
}