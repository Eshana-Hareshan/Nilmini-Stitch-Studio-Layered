package lk.ijse.nilmini_stitch_studio.bo.custom.impl;

import lk.ijse.nilmini_stitch_studio.bo.custom.OrderBO;
import lk.ijse.nilmini_stitch_studio.bo.custom.StockBO;
import lk.ijse.nilmini_stitch_studio.dao.custom.DAOFactory;
import lk.ijse.nilmini_stitch_studio.dao.custom.StockDAO;
import lk.ijse.nilmini_stitch_studio.dao.custom.impl.StockDAOImpl;
import lk.ijse.nilmini_stitch_studio.dto.StockDTO;
import lk.ijse.nilmini_stitch_studio.entity.Stock;

import java.util.ArrayList;
import java.util.List;

public class StockBOImpl implements StockBO {
    StockDAO stockDAO = (StockDAO) DAOFactory.getInstance().getDAOFactory(DAOFactory.DAOType.STOCK);

    public boolean saveStock(StockDTO stockDTO) throws Exception {
        Stock stock = new Stock(
                stockDTO.getItemName(),stockDTO.getQuantity(),stockDTO.getPrice(),
                stockDTO.getCustomerNumber(),stockDTO.getCustomerName(),stockDTO.getDateAdded(),stockDTO.getBorrowDate()
        );
        return stockDAO.save(stock);
    }

    public List<StockDTO> getAllStock() throws Exception {
        List<Stock> stocks = stockDAO.getAll();
        List<StockDTO> stockDTOs = new ArrayList<>();

        for (Stock stock : stocks) {
            StockDTO stockDTO = new StockDTO(stock.getItemId(),stock.getItemName(),stock.getQuantity(),stock.getPrice(),
                    stock.getCustomerNumber(),stock.getCustomerName(),stock.getDateAdded(),stock.getBorrowDate());
            stockDTOs.add(stockDTO);
        }

        return stockDTOs;
    }

    public boolean deleteStock(int itemId) throws Exception {
        return stockDAO.delete(String.valueOf(itemId));
    }
}
