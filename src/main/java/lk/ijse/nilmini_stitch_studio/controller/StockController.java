package lk.ijse.nilmini_stitch_studio.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.nilmini_stitch_studio.bo.custom.impl.CustomerBOImpl;
import lk.ijse.nilmini_stitch_studio.bo.custom.impl.StockBOImpl;
import lk.ijse.nilmini_stitch_studio.dao.custom.impl.StockDAOImpl;
import lk.ijse.nilmini_stitch_studio.dto.CustomerDTO;
import lk.ijse.nilmini_stitch_studio.dto.StockDTO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StockController implements Initializable {

    @FXML private TextField txtItemName, txtQuantity, txtPrice;
    @FXML private DatePicker dateAddedPicker, borrowDatePicker;
    @FXML private ComboBox<Number> comboCustomerNumber;
    @FXML private Label lblCustomerName;
    @FXML private TableView<StockDTO> tableStock;
    @FXML private TableColumn<StockDTO, Integer> colItemId;
    @FXML private TableColumn<StockDTO, String> colItemName;
    @FXML private TableColumn<StockDTO, Integer> colQuantity;
    @FXML private TableColumn<StockDTO, Double> colPrice;
    @FXML private TableColumn<StockDTO, Number> colCustomerNumber;
    @FXML private TableColumn<StockDTO, String> colCustomerName;
    @FXML private TableColumn<StockDTO, String> colDateAdded;
    @FXML private TableColumn<StockDTO, String> colBorrowDate;
    @FXML private TableColumn<StockDTO, Void> colAction;

    StockBOImpl stockBO = new StockBOImpl();

    CustomerBOImpl customerBO = new CustomerBOImpl();

    ObservableList<StockDTO> stockList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colCustomerNumber.setCellValueFactory(new PropertyValueFactory<>("customerNumber"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colDateAdded.setCellValueFactory(cell -> new SimpleStringProperty(
                cell.getValue().getDateAdded() != null ? cell.getValue().getDateAdded().toString() : "-"
        ));
        colBorrowDate.setCellValueFactory(cell -> new SimpleStringProperty(
                cell.getValue().getBorrowDate() != null ? cell.getValue().getBorrowDate().toString() : "-"
        ));

        loadCustomerCombo();
        comboCustomerNumber.setOnAction(e -> handleCustomerSelect());
        addRemoveButtonToTable();
        loadStockTable();
    }

    private void loadCustomerCombo() {
        try {
            List<CustomerDTO> customers = customerBO.getAllCustomers();
            ObservableList<Number> customerIds = FXCollections.observableArrayList();
            for (CustomerDTO c : customers) customerIds.add(c.getContactNumber());
            comboCustomerNumber.setItems(customerIds);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void handleCustomerSelect() {
        try {
            Number selected = comboCustomerNumber.getSelectionModel().getSelectedItem();
            if (selected != null) {
                CustomerDTO c = customerBO.searchCustomer(String.valueOf(selected.intValue()));
                lblCustomerName.setText(c != null ? c.getName() : "-");
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    private void saveStock() {
        try {
            Number customerNum = comboCustomerNumber.getSelectionModel().getSelectedItem();
            if (customerNum == null || txtItemName.getText().isEmpty() || txtQuantity.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Fill all required fields!").show();
                return;
            }

            StockDTO stock = new StockDTO(
                    txtItemName.getText(),
                    Integer.parseInt(txtQuantity.getText()),
                    Double.parseDouble(txtPrice.getText()),
                    customerNum.longValue(),
                    lblCustomerName.getText(),
                    java.sql.Date.valueOf(dateAddedPicker.getValue()),
                    java.sql.Date.valueOf(borrowDatePicker.getValue())
            );

            if (stockBO.saveStock(stock)) {
                new Alert(Alert.AlertType.INFORMATION, "Saved successfully!").show();
                resetFields();
                loadStockTable();
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void loadStockTable() {
        try {
            List<StockDTO> stocks = stockBO.getAllStock();
            stockList.setAll(stocks);
            tableStock.setItems(stockList);
        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    private void resetFields() {
        txtItemName.clear();
        txtQuantity.clear();
        txtPrice.clear();
        comboCustomerNumber.getSelectionModel().clearSelection();
        lblCustomerName.setText("-");
        dateAddedPicker.setValue(null);
        borrowDatePicker.setValue(null);
    }

    private void addRemoveButtonToTable() {
        colAction.setCellFactory(param -> new TableCell<>() {
            private final Button removeBtn = new Button("Remove");

            {
                removeBtn.getStyleClass().add("button-danger");
                removeBtn.setOnAction(event -> {
                    StockDTO stock = getTableView().getItems().get(getIndex());
                    try {
                        if (stockBO.deleteStock(stock.getItemId())) {
                            stockList.remove(stock);
                        }
                    } catch (Exception e) { e.printStackTrace(); }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : removeBtn);
            }
        });
    }
}
