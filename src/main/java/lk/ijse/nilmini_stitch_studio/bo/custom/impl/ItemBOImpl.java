package lk.ijse.nilmini_stitch_studio.bo.custom.impl;

import lk.ijse.nilmini_stitch_studio.bo.custom.ItemBO;
import lk.ijse.nilmini_stitch_studio.dao.custom.DAOFactory;
import lk.ijse.nilmini_stitch_studio.dao.custom.ItemDAO;
import lk.ijse.nilmini_stitch_studio.dao.custom.impl.ItemDAOImpl;
import lk.ijse.nilmini_stitch_studio.dto.ItemDTO;
import lk.ijse.nilmini_stitch_studio.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAOFactory(DAOFactory.DAOType.ITEM);

    public List<ItemDTO> getAllFabric() throws SQLException {

        List<Item>items = itemDAO.getAll();
        List<ItemDTO>itemsDTOs = new ArrayList<ItemDTO>();

        for(Item item : items){
            ItemDTO itemDTO = new ItemDTO(item.getId(),item.getFabric(),item.getQty(),item.getPrice());
            itemsDTOs.add(itemDTO);
        }
        return itemsDTOs;
    }

    public boolean deleteItem(int id) throws SQLException {
        return itemDAO.delete(String.valueOf(id));
    }

    public ItemDTO searchItem(String id) throws SQLException {
        Item item = itemDAO.search(id);
        return new ItemDTO(item.getId(),item.getFabric(),item.getQty(),item.getPrice());
    }

    public boolean saveItem(ItemDTO itemDTO) throws SQLException {
        Item item =new Item(itemDTO.getFabric(),itemDTO.getQty(),itemDTO.getPrice());
        return itemDAO.save(item);
    }

    public boolean updateItem(ItemDTO selected) throws SQLException {
        Item item =new Item(selected.getId(),selected.getFabric(),selected.getQty(),selected.getPrice());
        return itemDAO.update(item);
    }
}
