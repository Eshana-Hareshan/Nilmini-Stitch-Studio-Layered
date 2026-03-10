
package lk.ijse.nilmini_stitch_studio.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.nilmini_stitch_studio.bo.custom.BOFactory;
import lk.ijse.nilmini_stitch_studio.bo.custom.ItemBO;
import lk.ijse.nilmini_stitch_studio.bo.custom.impl.ItemBOImpl;
import lk.ijse.nilmini_stitch_studio.dao.custom.impl.ItemDAOImpl;
import lk.ijse.nilmini_stitch_studio.dto.ItemDTO;

public class ItemController implements Initializable {

    @FXML
    private TableView<ItemDTO> tblItem;

    @FXML
    private TableColumn<ItemDTO,String> colItemCategory;

    @FXML
    private TableColumn<ItemDTO,Integer> colItemQty;

    @FXML
    private TableColumn<ItemDTO,Double> colItemUnitPrice;

    @FXML
    private TableColumn<ItemDTO,Void> colAction;

    @FXML
    private TextField txtItemCategory;

    @FXML
    private TextField txtItemQuantity;

    @FXML
    private TextField txtItemUnitPrice;

    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.ITEM);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colItemCategory.setCellValueFactory(new PropertyValueFactory<>("fabric"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        colAction.setCellFactory(param -> new TableCell<>() {

            Button btn = new Button("Delete");

            {
                btn.setOnAction(event -> {

                    ItemDTO item = getTableView().getItems().get(getIndex());

                    try {

                        boolean isDeleted = itemBO.deleteItem(item.getId());

                        if(isDeleted) {

                            new Alert(Alert.AlertType.INFORMATION, "Item delete successfully!").show();

                            cleanFields();

                        } else {

                            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();

                        }

                        loadItemTable();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }

        });

        tblItem.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, selectedItem) -> {

            if(selectedItem != null){

                txtItemCategory.setText(selectedItem.getFabric());
                txtItemQuantity.setText(String.valueOf(selectedItem.getQty()));
                txtItemUnitPrice.setText(String.valueOf(selectedItem.getPrice()));

            }

        });

        loadItemTable();

    }

    private void loadItemTable(){

        try{

            List<ItemDTO> itemList = itemBO.getAllFabric();

            ObservableList<ItemDTO> obList = FXCollections.observableArrayList();

            obList.addAll(itemList);

            tblItem.setItems(obList);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    private void saveItem(){

        try{

            String category = txtItemCategory.getText();
            int qty = Integer.parseInt(txtItemQuantity.getText());
            double price = Double.parseDouble(txtItemUnitPrice.getText());

            ItemDTO item = new ItemDTO(category,qty,price);

            boolean isSaved = itemBO.saveItem(item);

            if(isSaved) {

                new Alert(Alert.AlertType.INFORMATION, "Item save successfully!").show();

                cleanFields();

            } else {

                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();

            }


            if(isSaved){

                loadItemTable();
                cleanFields();

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    private void updateItem(){

        try{

            ItemDTO selected = tblItem.getSelectionModel().getSelectedItem();

            if(selected == null) return;

            selected.setFabric(txtItemCategory.getText());
            selected.setQty(Integer.parseInt(txtItemQuantity.getText()));
            selected.setPrice(Double.parseDouble(txtItemUnitPrice.getText()));

            boolean isUpdated = itemBO.updateItem(selected);

            if(isUpdated) {

                new Alert(Alert.AlertType.INFORMATION, "Item updated successfully!").show();

                cleanFields();

            } else {

                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();

            }

            loadItemTable();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    private void resetItem(){

        cleanFields();

    }

    private void cleanFields(){

        txtItemCategory.setText("");
        txtItemQuantity.setText("");
        txtItemUnitPrice.setText("");

    }

}