package lk.ijse.nilmini_stitch_studio.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.nilmini_stitch_studio.App;
import lk.ijse.nilmini_stitch_studio.bo.custom.BOFactory;
import lk.ijse.nilmini_stitch_studio.bo.custom.CustomerBO;
import lk.ijse.nilmini_stitch_studio.bo.custom.ItemBO;
import lk.ijse.nilmini_stitch_studio.bo.custom.OrderBO;
import lk.ijse.nilmini_stitch_studio.bo.custom.impl.CustomerBOImpl;
import lk.ijse.nilmini_stitch_studio.bo.custom.impl.ItemBOImpl;
import lk.ijse.nilmini_stitch_studio.bo.custom.impl.OrderBOImpl;
import lk.ijse.nilmini_stitch_studio.dto.CustomerDTO;
import lk.ijse.nilmini_stitch_studio.dto.ItemDTO;
import lk.ijse.nilmini_stitch_studio.dto.OrderDTO;
import lk.ijse.nilmini_stitch_studio.dto.OrderItemDTO;
import lk.ijse.nilmini_stitch_studio.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.nilmini_stitch_studio.dao.custom.impl.ItemDAOImpl;
import lk.ijse.nilmini_stitch_studio.dto.OrderItemTM;
import lk.ijse.nilmini_stitch_studio.dao.custom.impl.OrderDAOImpl;


public class OrderController implements Initializable {
    
    @FXML
    private ComboBox<Number> comboButton;

    @FXML
    private ComboBox<Number> comboCustomerId;

    @FXML
    private ComboBox<Number> comboFabric;
    
    @FXML
    private Label lblCustomerNameValue;
    
    @FXML
    private Label lblItemFabricValue;
    @FXML
    private Label lblItemButtonValue;
    @FXML
    private Label lblItemThreadValue;
    @FXML
    private Label lblItemIlasticValue;
    @FXML
    private Label lblItemMenValue;
    @FXML
    private Label lblItemWomenValue;
    @FXML
    private Label lblOrderTotal;
    
    @FXML
    private Label lblItemFabricPrice;
    @FXML
    private Label lblItemButtonPrice;
    
    @FXML
    private TextField qtyFabricField;
    
    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotalPrice;

    @FXML
    private TableColumn<?, ?> colUnitPrice;
    @FXML
    private TableColumn<?, ?> colItemId;
    @FXML
    private TableColumn<OrderItemTM, Void> colAction;
    
    @FXML
    private TableView<OrderItemTM> tblOrderItem;
        
    
    private final ObservableList<OrderItemTM> orderItemObList = FXCollections.observableArrayList();
    
    
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.CUSTOMER);

    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.ITEM);

    OrderBO orderBO = (OrderBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.ORDER);


    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colItemName.setCellValueFactory(new PropertyValueFactory<>("fabric"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        
               
        colAction.setCellFactory(cell -> new TableCell<OrderItemTM, Void>() {
        
            Button btn = new Button("Remove");
            
            {
                btn.setOnAction(event -> {
                    
                    OrderItemTM item = getTableView().getItems().get(getIndex());
                    orderItemObList.remove(item);
                    loadOrderItemTbl();
                    
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item,empty);
                setGraphic(empty?null:btn);
            }
        });
        loadComboCustomerId();
        loadComboItemFabric();
    
    }
    
    @FXML
    public void clickBack()throws IOException { 
        App.setRoot("layout");
    }
    
    private void loadComboCustomerId() {
        try {
        
            List<CustomerDTO> customerList = customerBO.getAllCustomers();
            
            ObservableList<Number> customerIdObList =  FXCollections.observableArrayList();
            
            for (CustomerDTO customerDTO : customerList) {
                customerIdObList.add(customerDTO.getContactNumber());
            }
            
            comboCustomerId.setItems(customerIdObList);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    void handleSelectComboCustomerId(ActionEvent event) {

        try {
        
            Number selectedId = comboCustomerId.getSelectionModel().getSelectedItem();
            int selecteCustomerId = selectedId.intValue();

            CustomerDTO customerDTO = customerBO.searchCustomer(String.valueOf(selecteCustomerId));
            
            if(customerDTO!=null) {
            
                lblCustomerNameValue.setText(customerDTO.getName());
                
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer not found!").show();
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadComboItemFabric() {
        try {

            List<ItemDTO> itemList = itemBO.getAllFabric();
            
            ObservableList<Number> itemIdObList =  FXCollections.observableArrayList();
            
            for (ItemDTO itemDTO : itemList) {
                itemIdObList.add(itemDTO.getId());
            }
            
            comboFabric.setItems(itemIdObList);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void handleSelectComboFabric(ActionEvent event) {
        
        try {
        
            Number selectedId = comboFabric.getSelectionModel().getSelectedItem();
            int selectItemrId = selectedId.intValue();

            ItemDTO itemDTO = itemBO.searchItem(String.valueOf(selectItemrId));
            
            if(itemDTO!=null) {
            
                lblItemFabricValue.setText(itemDTO.getFabric());
                lblItemFabricPrice.setText(String.valueOf(itemDTO.getPrice()));
                
            } else {
                new Alert(Alert.AlertType.ERROR, "Item not found!").show();
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    
    
    
    
    @FXML
    private void handleAddToCart(ActionEvent event) {

        Number selectedId = comboFabric.getSelectionModel().getSelectedItem();
        int itemId = selectedId.intValue();
        
        String itemFabric = lblItemFabricValue.getText();
        String itemPrice = lblItemFabricPrice.getText();
        String qty = qtyFabricField.getText();
        double totalItemPrice = Double.parseDouble(itemPrice) * Integer.parseInt(qty);
        
        OrderItemTM orderItemTM = new OrderItemTM(itemId, itemFabric,Double.parseDouble(itemPrice), Integer.parseInt(qty), totalItemPrice);
        
        orderItemObList.add(orderItemTM);
        
        loadOrderItemTbl();
        
    }
    
    private void loadOrderItemTbl() {
    
        tblOrderItem.setItems(orderItemObList);
        calcOrderTotal();
        
    }
    
    private void calcOrderTotal() {
        
        double total = 0.0;
        
        for (OrderItemTM orderItemTM : orderItemObList) {
            total+=orderItemTM.getTotalPrice();
        }
        
        lblOrderTotal.setText(String.valueOf(total));
        
        qtyFabricField.setText("");

        
    }
    
    @FXML
    void handlePlaceOrder(ActionEvent event) {
        
        try{
            Number selectedId = comboCustomerId.getSelectionModel().getSelectedItem();
            int customerId = selectedId.intValue();
            
            List<OrderItemDTO> orderItemList = new ArrayList<>();
                    
            for (OrderItemTM orderItemTM : orderItemObList){
                
                OrderItemDTO orderItem = new OrderItemDTO(
                    orderItemTM.getItemId(),
                    orderItemTM.getQty(),
                    orderItemTM.getPrice());
                
                orderItemList.add(orderItem);
            }

              OrderDTO orderDTO = new OrderDTO(customerId, new Date(), orderItemList);

              int result = orderBO.placeOrder(orderDTO);
              
              if(result>0) {
                new Alert(Alert.AlertType.INFORMATION, "Order Placed Successfully!").show();
                
                orderBO.print(result);
                
              } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
              }
            
        } catch(Exception e) {
            e.printStackTrace();
        }                      
    }                
}
