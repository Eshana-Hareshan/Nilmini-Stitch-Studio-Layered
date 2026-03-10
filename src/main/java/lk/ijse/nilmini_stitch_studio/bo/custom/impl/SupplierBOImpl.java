package lk.ijse.nilmini_stitch_studio.bo.custom.impl;

import lk.ijse.nilmini_stitch_studio.bo.custom.SupplierBO;
import lk.ijse.nilmini_stitch_studio.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.nilmini_stitch_studio.dto.SupplierDTO;
import lk.ijse.nilmini_stitch_studio.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAOImpl supplierDAO = new SupplierDAOImpl();

    public boolean deleteSupplier(String contactNumber) throws SQLException {
        return supplierDAO.delete(contactNumber);
    }

    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException {
        Supplier supplier = new Supplier(supplierDTO.getName(),supplierDTO.getCatogory(),
                supplierDTO.getPrice(),supplierDTO.getSNumber(),supplierDTO.getDate());
        return supplierDAO.save(supplier);
    }

    public boolean updateSupplier(SupplierDTO selected) throws SQLException {
        Supplier supplier= new Supplier(selected.getName(),selected.getCatogory(),selected.getPrice(),selected.getSNumber());

        return supplierDAO.update(supplier);
    }

    public List<SupplierDTO> getAllSuppliers() throws SQLException {
        List<Supplier> suppliers = supplierDAO.getAll();
        List<SupplierDTO> supplierDTOs = new ArrayList<>();

        for(Supplier supplier : suppliers){
           SupplierDTO supplierDTO = new SupplierDTO(
                   supplier.getName(),supplier.getCatogory(),supplier.getPrice(),supplier.getSNumber(),supplier.getDate()
           );
            supplierDTOs.add(supplierDTO);
        }
        return supplierDTOs;
    }

    public void printSupplierReport() {
    }
}
