package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProductController {

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colQuantityOnHand111;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableView<?> tblItems;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtProductId;

    @FXML
    private JFXTextField txtQuantityOnHand;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXTextField txtSize;

    @FXML
    private JFXTextField txtSupplierId;

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void itemReloadBtnOnAction(ActionEvent event) {

    }

    @FXML
    void productAddBtnOnAction(ActionEvent event) {

    }

    @FXML
    void productClearBtnOnAction(ActionEvent event) {

    }

    @FXML
    void productDeleteBtnOnAction(ActionEvent event) {

    }

    @FXML
    void productUpdateBtnOnAction(ActionEvent event) {

    }

}
