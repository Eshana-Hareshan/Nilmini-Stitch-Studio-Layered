package lk.ijse.nilmini_stitch_studio.bo.custom;

import lk.ijse.nilmini_stitch_studio.bo.SuperBO;
import lk.ijse.nilmini_stitch_studio.dto.OrderDTO;

import java.sql.SQLException;

public interface OrderBO extends SuperBO {
    public int placeOrder(OrderDTO orderDTO) throws SQLException;

    public void print(int result);
}
