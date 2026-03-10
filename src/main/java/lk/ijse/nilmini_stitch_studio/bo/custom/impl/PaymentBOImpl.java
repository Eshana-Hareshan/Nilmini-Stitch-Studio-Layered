package lk.ijse.nilmini_stitch_studio.bo.custom.impl;

import lk.ijse.nilmini_stitch_studio.bo.custom.PaymentBO;
import lk.ijse.nilmini_stitch_studio.dao.custom.DAOFactory;
import lk.ijse.nilmini_stitch_studio.dao.custom.PaymentDAO;
import lk.ijse.nilmini_stitch_studio.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.nilmini_stitch_studio.dto.PaymentDTO;
import lk.ijse.nilmini_stitch_studio.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAOFactory(DAOFactory.DAOType.PAYMENT);

    public List<PaymentDTO> customerPaymentDetails(String id) throws SQLException {

        List<Payment> paymentCustomer = paymentDAO.customerPaymentDetails(id);
        List<PaymentDTO> paymentDTOS = new ArrayList<>();

        for (Payment payment : paymentCustomer) {
            PaymentDTO paymentDTO = new PaymentDTO(payment.getCName(),payment.getFabric(),payment.getDate(),payment.getPrice());
            paymentDTOS.add(paymentDTO);
        }
        return paymentDTOS;
    }
}
