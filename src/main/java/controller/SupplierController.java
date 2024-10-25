package controller;

import com.jfoenix.controls.JFXTextField;
import dto.SupplierDto;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.SupplierService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colProduct;

    @FXML
    private TableView<SupplierDto> tblSuppliers;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtProduct;

    @FXML
    private TextField txtSearch;

    SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colProduct.setCellValueFactory(new PropertyValueFactory<>("product"));

        tblSuppliers.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setTextToValues(newValue);
        }));
        loadTable();
    }

    private void setTextToValues(SupplierDto newValue){
        txtId.setText(newValue.getSupplierId().toString());
        txtName.setText(newValue.getName());
        txtEmail.setText(newValue.getEmail());
        txtCompany.setText(newValue.getCompany());
        txtProduct.setText(newValue.getProduct());
    }

    void loadTable() {
        ObservableList<SupplierDto> suppliersDtoObservableList = supplierService.getAllSuppliers();
        tblSuppliers.setItems(suppliersDtoObservableList);
    }
    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {
        SupplierDto supplierDto = new SupplierDto(
                Integer.parseInt(txtId.getText()),
                txtName.getText(),
                txtCompany.getText(),
                txtEmail.getText(),
                txtProduct.getText()
        );

        if(supplierService.addSupplier(supplierDto)){
            new Alert(Alert.AlertType.INFORMATION,"Supplier Added !!").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Supplier Not Added :(").show();
        }
    }

    @FXML
    void btnDeleteSupplierOnAction(ActionEvent event) {
        SupplierDto selectedSupplier = tblSuppliers.getSelectionModel().getSelectedItem();

        if (selectedSupplier == null) {
            new Alert(Alert.AlertType.ERROR,"No supplier selected for deletion.").show();
            return;
        }
        String supplierId = String.valueOf(selectedSupplier.getSupplierId());
        supplierService.deleteSupplier(supplierId);

        loadTable();

    }

    @FXML
    void btnLoadTableBtnOnAction(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String searchId = txtSearch.getText().trim();

        if (searchId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING,"Please enter an supplier ID.").show();
            return;
        }

        SupplierDto supplierDto = supplierService.search(searchId);

        if (supplierDto != null) {
            txtId.setText(supplierDto.getSupplierId().toString());
            txtName.setText(supplierDto.getName());
            txtEmail.setText(supplierDto.getEmail());
            txtCompany.setText(supplierDto.getCompany());
            txtProduct.setText(supplierDto.getProduct());
        } else {
            new Alert(Alert.AlertType.WARNING,"No supplier found with ID.").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        SupplierDto selectedSupplier = tblSuppliers.getSelectionModel().getSelectedItem();

        if (selectedSupplier == null) {
            System.out.println("No supplier selected for update.");
            return;
        }

        String updatedName = txtName.getText();
        String updatedCompany = txtCompany.getText();
        String updatedEmail = txtEmail.getText();
        String updateProduct = txtProduct.getText();

        selectedSupplier.setName(updatedName);
        selectedSupplier.setCompany(updatedCompany);
        selectedSupplier.setEmail(updatedEmail);
        selectedSupplier.setProduct(updateProduct);

        boolean isUpdated = supplierService.updateSupplier(selectedSupplier);
        loadTable();

        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION,"Supplier Updated !!").show();
            loadTable();
        } else {
            new Alert(Alert.AlertType.ERROR,"Supplier not Updated !!").show();
        }
    }


    public void btnClearOnAction(ActionEvent actionEvent) {
        txtId.setText(null);
        txtName.setText(null);
        txtEmail.setText(null);
        txtCompany.setText(null);
        txtProduct.setText(null);
    }
}
