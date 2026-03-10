package lk.ijse.nilmini_stitch_studio.controller;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.nilmini_stitch_studio.bo.custom.impl.SupplierBOImpl;
import lk.ijse.nilmini_stitch_studio.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.nilmini_stitch_studio.dto.SupplierDTO;
import net.sf.jasperreports.engine.JRException;

public class SupplierController implements Initializable {

    @FXML private TextField supplierName;
    @FXML private TextField supplierCatogory;
    @FXML private TextField supplierPrice;
    @FXML private TextField supplierNumber;

    @FXML private TableView<SupplierDTO> tableSupplier;
    @FXML private TableColumn<SupplierDTO, String> colName;
    @FXML private TableColumn<SupplierDTO, String> colCatogory;
    @FXML private TableColumn<SupplierDTO, Double> colPrice;
    @FXML private TableColumn<SupplierDTO, Long> colNumber;
    @FXML private TableColumn<SupplierDTO, Date> colDate;
    @FXML private TableColumn<SupplierDTO, Void> colAction;

    SupplierBOImpl supplierBO = new SupplierBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCatogory.setCellValueFactory(new PropertyValueFactory<>("catogory"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("sNumber"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        setupActionColumn();
        tableSupplier.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, sel) -> {
            if (sel != null) fillFields(sel);
        });

        loadSupplierTable();
    }

    private void setupActionColumn() {
        colAction.setCellFactory(column -> new TableCell<>() {
            private final Button btn = new Button("Delete");
            {
                btn.setStyle("-fx-background-color:#dc2626; -fx-text-fill:white;");
                btn.setOnAction(e -> {
                    SupplierDTO supplier = getTableView().getItems().get(getIndex());
                    try {
                        boolean isDeleted = supplierBO.deleteSupplier(String.valueOf(supplier.getSNumber()));
                        if (isDeleted) {
                            new Alert(Alert.AlertType.INFORMATION, "Supplier deleted successfully!").show();
                            cleanFields();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Error deleting supplier!").show();
                        }
                        loadSupplierTable();
                        cleanFields();
                    } catch (Exception ex) { ex.printStackTrace(); }
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
    }

    @FXML
    private void saveSupplier() {
        try {
            String name = supplierName.getText().trim();
            String cat = supplierCatogory.getText().trim();
            String priceTxt = supplierPrice.getText().trim();
            String numberTxt = supplierNumber.getText().trim();

            if (name.isEmpty() || numberTxt.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill all fields!").show();
                return;
            }
            if (!numberTxt.matches("\\d{10}")) {
                new Alert(Alert.AlertType.ERROR, "Contact number must be 10 digits!").show();
                return;
            }

            double price = priceTxt.isEmpty() ? 0 : Double.parseDouble(priceTxt);
            long sNumber = Long.parseLong(numberTxt);

            SupplierDTO dto = new SupplierDTO(name, cat, price, sNumber, new Date());
            boolean saved = supplierBO.saveSupplier(dto);

            if (saved) {
                new Alert(Alert.AlertType.INFORMATION, "Supplier saved successfully!").show();
                cleanFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Error saving supplier!").show();
            }
            loadSupplierTable();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: Possibly duplicate contact number!").show();
        }
    }

    @FXML
    private void handleSupplierUpdate() {
        try {
            SupplierDTO selected = tableSupplier.getSelectionModel().getSelectedItem();
            if (selected == null) return;

            String name = supplierName.getText().trim();
            String cat = supplierCatogory.getText().trim();
            double price = Double.parseDouble(supplierPrice.getText().trim());

            selected.setName(name);
            selected.setCatogory(cat);
            selected.setPrice(price);

            boolean updated = supplierBO.updateSupplier(selected);
            if (updated) {
                new Alert(Alert.AlertType.INFORMATION, "Supplier updated successfully!").show();
                cleanFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Error updating supplier!").show();
            }
            loadSupplierTable();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    @FXML
    private void handleSupplierReset() { cleanFields(); }

    @FXML
    private void handlePrint() {
        try { supplierBO.printSupplierReport(); } catch (Exception e) { e.printStackTrace(); }
    }

    private void loadSupplierTable() {
        try {
            List<SupplierDTO> list = supplierBO.getAllSuppliers();
            ObservableList<SupplierDTO> obList = FXCollections.observableArrayList(list);
            tableSupplier.setItems(obList);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void cleanFields() {
        supplierName.clear();
        supplierCatogory.clear();
        supplierPrice.clear();
        supplierNumber.clear();
    }

    private void fillFields(SupplierDTO sup) {
        supplierName.setText(sup.getName());
        supplierCatogory.setText(sup.getCatogory());
        supplierPrice.setText(String.valueOf(sup.getPrice()));
        supplierNumber.setText(String.valueOf(sup.getSNumber()));
    }
}