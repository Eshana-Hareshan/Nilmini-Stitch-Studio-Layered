package lk.ijse.nilmini_stitch_studio.bo.custom;

import lk.ijse.nilmini_stitch_studio.bo.SuperBO;
import lk.ijse.nilmini_stitch_studio.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory instance;

    private BOFactory() {}
    public static BOFactory getInstance() {return instance==null?instance=new BOFactory():instance;}

    public enum BOTypes{
        CUSTOMER,ITEM,ORDER,PAYMENT,STOCK,SUPPLIER
    }

    public SuperBO getBOFactory(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            default:
                return null;
        }

    }
}
