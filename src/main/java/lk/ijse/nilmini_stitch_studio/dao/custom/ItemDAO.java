package lk.ijse.nilmini_stitch_studio.dao.custom;

import lk.ijse.nilmini_stitch_studio.dao.CrudDAO;
import lk.ijse.nilmini_stitch_studio.dao.SuperDAO;
import lk.ijse.nilmini_stitch_studio.entity.Item;
import lk.ijse.nilmini_stitch_studio.util.CrudUtil;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item> {

    public boolean deceaseQTY(int itemId,int qty)throws SQLException ;

}
