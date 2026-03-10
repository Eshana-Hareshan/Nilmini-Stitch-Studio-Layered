package lk.ijse.nilmini_stitch_studio.bo.custom;

import lk.ijse.nilmini_stitch_studio.bo.SuperBO;
import lk.ijse.nilmini_stitch_studio.dto.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    public boolean deleteItem(int id) throws SQLException;

    public List<ItemDTO> getAllFabric() throws SQLException;

    public ItemDTO searchItem(String s) throws SQLException;

    boolean saveItem(ItemDTO item)throws SQLException;

    boolean updateItem(ItemDTO selected)throws SQLException;
}
