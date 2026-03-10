package lk.ijse.nilmini_stitch_studio.dao.custom;

import lk.ijse.nilmini_stitch_studio.dao.SuperDAO;
import lk.ijse.nilmini_stitch_studio.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory instance;

    public static DAOFactory getInstance() {return instance==null?instance=new DAOFactory():instance;}

    public enum DAOType{
        CUSTOMER,ITEM,ORDER,ORDERITEM,PAYMENT,STOCK,SUPPLIER
    }

    public SuperDAO getDAOFactory(DAOType daoType){
        switch (daoType) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERITEM:
                return new OrderItemDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case STOCK:
                return new StockDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            default:
                return null;
        }
    }
}
