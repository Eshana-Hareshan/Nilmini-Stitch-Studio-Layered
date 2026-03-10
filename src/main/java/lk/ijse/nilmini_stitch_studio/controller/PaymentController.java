package lk.ijse.nilmini_stitch_studio.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.nilmini_stitch_studio.App;

public class PaymentController implements Initializable {

    @FXML
    private AnchorPane mainContent;

    @FXML
    private Button btnCustomerPayment, btnSupplierPayment;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            Parent customerFXML = App.loadFXML("customerPayment");
            mainContent.getChildren().setAll(customerFXML);
    
        }catch(Exception e){}
    }

    @FXML
    public void clickCustomerPayment() throws IOException {
        setActive(btnCustomerPayment);
        Parent customerFXML = App.loadFXML("customerPayment");
        mainContent.getChildren().setAll(customerFXML);
    }

    @FXML
    public void clickSupplierPayment() throws IOException {
        setActive(btnSupplierPayment);
        Parent supplierFXML = App.loadFXML("supplierPayment");
        mainContent.getChildren().setAll(supplierFXML);
    }

    private void setActive(Button clickedBtn) {
        clearActive();
        clickedBtn.getStyleClass().add("active");
    }

    private void clearActive() {
        Button[] buttons = { btnCustomerPayment, btnSupplierPayment };
        for (Button btn : buttons) {
            btn.getStyleClass().remove("active");
        }
    }
}
