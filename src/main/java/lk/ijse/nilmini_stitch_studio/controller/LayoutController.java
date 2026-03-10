package lk.ijse.nilmini_stitch_studio.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.nilmini_stitch_studio.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LayoutController implements Initializable {

    @FXML
    private AnchorPane mainContent;

    @FXML
    private Button btnCustomer, btnOrder, btnItem, btnPayment, btnSupplier, btnStock;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadPage(btnCustomer,"customers");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPage(Button btn,String fxml) throws IOException {

        clearActive();
        btn.getStyleClass().add("active");

        Parent page = App.loadFXML(fxml);

        AnchorPane.setTopAnchor(page,0.0);
        AnchorPane.setBottomAnchor(page,0.0);
        AnchorPane.setLeftAnchor(page,0.0);
        AnchorPane.setRightAnchor(page,0.0);

        mainContent.getChildren().setAll(page);
    }

    @FXML
    void clickCustomer() throws IOException {
        loadPage(btnCustomer,"customers");
    }

    @FXML
    void clickOrder() throws IOException {
        loadPage(btnOrder,"order");
    }

    @FXML
    void clickItem() throws IOException {
        loadPage(btnItem,"item");
    }

    @FXML
    void clickPayment() throws IOException {
        loadPage(btnPayment,"customerPayment");
    }

    @FXML
    void clickStock() throws IOException {
        loadPage(btnStock,"stock");
    }

    @FXML
    void clickSupplier() throws IOException {
        loadPage(btnSupplier,"supplier");
    }

    @FXML
    void clickBack() throws IOException {
        App.setRoot("login");
    }

    private void clearActive(){
        btnCustomer.getStyleClass().remove("active");
        btnOrder.getStyleClass().remove("active");
        btnItem.getStyleClass().remove("active");
        btnPayment.getStyleClass().remove("active");
        btnSupplier.getStyleClass().remove("active");
        btnStock.getStyleClass().remove("active");
    }
}