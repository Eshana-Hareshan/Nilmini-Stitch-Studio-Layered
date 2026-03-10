package lk.ijse.nilmini_stitch_studio.controller;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.nilmini_stitch_studio.bo.custom.BOFactory;
import lk.ijse.nilmini_stitch_studio.bo.custom.CustomerBO;
import lk.ijse.nilmini_stitch_studio.bo.custom.impl.CustomerBOImpl;
import lk.ijse.nilmini_stitch_studio.dto.CustomerDTO;
import lk.ijse.nilmini_stitch_studio.dao.custom.impl.CustomerDAOImpl;

public class CustomersController implements Initializable {

    @FXML
    private TextField customerId;
    @FXML
    private TextField customerName;
    @FXML
    private TextField customerDate;
    @FXML
    private TextField customerNumber;

    @FXML
    private TableView<CustomerDTO> tableCustomer;

    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colDate;
    @FXML
    private TableColumn colNumber;
     @FXML
    private TableColumn<CustomerDTO, Void> colAction;


    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        colAction.setCellFactory(cell -> new TableCell<CustomerDTO, Void>() {

            Button btn = new Button("Delete");

            {
                btn.setOnAction(event -> {
                    CustomerDTO item = getTableView().getItems().get(getIndex());
                    try {
                        customerBO.deleteCustomer(String.valueOf(item.getContactNumber()));


                        loadCustomerTable();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                tableCustomer.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, selectedCustomer) -> {
                    if (selectedCustomer != null) {
                        customerName.setText(selectedCustomer.getName());
                        customerNumber.setText(String.valueOf(selectedCustomer.getContactNumber()));
                    }
        }
    );
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item,empty);
                setGraphic(empty?null:btn);
            }
        });


        loadCustomerTable();

    }


    @FXML
    private void saveCustomer() {
        String name = customerName.getText().trim();
        String cNumber = customerNumber.getText().trim();

    if (name.isEmpty() || cNumber.isEmpty()) {
        new Alert(Alert.AlertType.ERROR, "Please fill all fields!").show();
        return;
    }

    if (!cNumber.matches("\\d{10}")) {
        new Alert(Alert.AlertType.ERROR,"Contact number must contain exactly 10 digits!")
        .show();
        return;
    }

    try {
        CustomerDTO cusDTO = new CustomerDTO(name,Long.parseLong(cNumber),new Date());

        boolean isSaved = customerBO.saveCustomer(cusDTO);

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION,"Customer saved successfully!").show();
            cleanFields();
        } else {
            new Alert(Alert.AlertType.ERROR,"Sorry! Something went wrong!").show();
        }

    } catch (Exception e) {
        e.printStackTrace();
        new Alert(Alert.AlertType.ERROR,"Already added this contact number!").show();
    }

    loadCustomerTable();
}


//    @FXML
//    private void handleSearchCustomer(KeyEvent event) {
//
//        if(event.getCode() == KeyCode.ENTER) {
//
//            String id = customerId.getText();
//
//            try {
//
//                CustomerDTO customerDTO = customerDAOImpl.searchCustomer(id);
//
//                if(customerDTO!=null) {
//                    customerName.setText(customerDTO.getName());
//                    customerNumber.setText(String.valueOf(customerDTO.getContactNumber()));
//                    customerDate.setText(String.valueOf(customerDTO.getDate()));
//                } else {
//                    new Alert(Alert.AlertType.ERROR, "Customer not found!").show();
//                }
//
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }

    @FXML
    private void handleCustomerUpdate() throws ParseException {

        String name = customerName.getText();
        String cNumber = customerNumber.getText();



        try {

            CustomerDTO cusDTO = new CustomerDTO(name,Integer.parseInt(cNumber));

            boolean isUpdated = customerBO.updateCustomer(cusDTO);

            if(isUpdated) {

                new Alert(Alert.AlertType.INFORMATION, "Customer updated successfully!").show();

                cleanFields();

            } else {

                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();

            }

        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }

        loadCustomerTable();

    }

    private void loadCustomerTable() {
         try {

            List<CustomerDTO> customerList = customerBO.getAllCustomers();

            ObservableList<CustomerDTO> obList = FXCollections.observableArrayList();

            for (CustomerDTO customerDTO : customerList) {
                obList.add(customerDTO);
            }

            tableCustomer.setItems(obList);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCustomerReset() {

        cleanFields();

    }

    @FXML
    void handlePrint(ActionEvent event) {
        try{
            customerBO.printCustomerReport();
        }catch(Exception e){}

    }

    private void cleanFields() {

        customerName.setText("");
        customerNumber.setText("");

    }


}
