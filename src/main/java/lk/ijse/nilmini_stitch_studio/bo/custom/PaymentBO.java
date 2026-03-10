package lk.ijse.nilmini_stitch_studio.bo.custom;

import lk.ijse.nilmini_stitch_studio.bo.SuperBO;
import lk.ijse.nilmini_stitch_studio.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {
    public List<PaymentDTO> customerPaymentDetails(String s) throws SQLException;
}
