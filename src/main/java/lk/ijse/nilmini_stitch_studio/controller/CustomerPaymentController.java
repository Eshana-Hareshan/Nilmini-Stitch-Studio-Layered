package lk.ijse.nilmini_stitch_studio.controller;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.nilmini_stitch_studio.bo.custom.BOFactory;
import lk.ijse.nilmini_stitch_studio.bo.custom.CustomerBO;
import lk.ijse.nilmini_stitch_studio.bo.custom.PaymentBO;
import lk.ijse.nilmini_stitch_studio.bo.custom.impl.CustomerBOImpl;
import lk.ijse.nilmini_stitch_studio.bo.custom.impl.PaymentBOImpl;
import lk.ijse.nilmini_stitch_studio.dto.CustomerDTO;
import lk.ijse.nilmini_stitch_studio.dto.PaymentDTO;

public class CustomerPaymentController implements Initializable {
    
    @FXML
    private AnchorPane mainContent;
    
    @FXML
    private ComboBox<Long> comboCustomerId;
    
    @FXML
    private TableColumn<?, ?> colCustomerAmount;

    @FXML
    private TableColumn<?, ?> colCustomerDate;

    @FXML
    private TableColumn<?, ?> colCustomerDetails;

    @FXML
    private TableColumn<?, ?> colCustomerName;
    
    @FXML
    private Label lblCustomerNameValue;
    
    @FXML
    private Label lblOrderTotal;
        
    @FXML
    private TableView<PaymentDTO> tblCustomerPayments;
    
    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.PAYMENT);
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.CUSTOMER);

    ObservableList<PaymentDTO> paymentObList = FXCollections.observableArrayList() ;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("cName"));
        colCustomerDetails.setCellValueFactory(new PropertyValueFactory<>("fabric"));
        colCustomerDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCustomerAmount.setCellValueFactory(new PropertyValueFactory<>("price"));

        loadComboCustomerId();

    }
    
    private void loadComboCustomerId() {
        try {
        
            List<CustomerDTO> customerList = customerBO.getAllCustomers();
            
            ObservableList<Long> customerIdObList =  FXCollections.observableArrayList();
            
            for (CustomerDTO customerDTO : customerList) {
                customerIdObList.add(customerDTO.getContactNumber());
            }
            
            comboCustomerId.setItems(customerIdObList);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void selectComboCustomerID(ActionEvent event){
        
        try{
        
            Number selectedId = comboCustomerId.getSelectionModel().getSelectedItem();

            List<PaymentDTO> paymentDTO = paymentBO.customerPaymentDetails(String.valueOf(selectedId));
            
            paymentObList.clear();
            
            String name;
            
            for(PaymentDTO payment:paymentDTO){
                lblCustomerNameValue.setText(payment.getCName());
                String fabric = payment.getFabric();
                Date date = payment.getDate();
                double price = payment.getPrice();
                
                PaymentDTO paymentObDTO = new PaymentDTO(fabric,date,price);
                
                paymentObList.add(paymentObDTO);
            }
            
        }catch(Exception e){}
        loadPaymentTbl();

    }
    
    private void loadPaymentTbl() {
    
        tblCustomerPayments.setItems(paymentObList);
        calcOrderTotal();
        
    }
    
    private void calcOrderTotal() {
        
        double total = 0.0;
        
        for (PaymentDTO paymentDTO : paymentObList) {
            total+=paymentDTO.getPrice();
        }
        
        lblOrderTotal.setText(String.valueOf(total));
        
        
    }
    
}
