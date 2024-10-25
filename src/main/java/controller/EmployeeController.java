package controller;

import com.jfoenix.controls.JFXTextField;
import dto.EmployeeDto;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.EmployeeService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<EmployeeDto> tblEmployees;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private TextField txtSearch;

    EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));

        tblEmployees.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setTextToValues(newValue);
        }));
        loadTable();
    }

    private void setTextToValues(EmployeeDto newValue){
        txtId.setText(newValue.getEmpId().toString());
        txtName.setText(newValue.getName());
        txtEmail.setText(newValue.getEmail());
        txtCompany.setText(newValue.getCompany());

    }

    void loadTable() {
        ObservableList<EmployeeDto> employeeDtoObservableList = employeeService.getAllEmployees();
        tblEmployees.setItems(employeeDtoObservableList);
    }

    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) {
        EmployeeDto employeeDto = new EmployeeDto(
                Integer.parseInt(txtId.getText()),
                txtName.getText(),
                txtCompany.getText(),
                txtEmail.getText()
        );

        if(employeeService.addEmployee(employeeDto)){
            new Alert(Alert.AlertType.INFORMATION,"Employee Added !!").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Employee Not Added :(").show();
        }
    }

    @FXML
    void btnUpdateEmployeeOnAction(ActionEvent event) {
        EmployeeDto selectedEmployee = tblEmployees.getSelectionModel().getSelectedItem();

        if (selectedEmployee == null) {
            System.out.println("No employee selected for update.");
            return;
        }

        String updatedName = txtName.getText();
        String updatedCompany = txtCompany.getText();
        String updatedEmail = txtEmail.getText();

        selectedEmployee.setName(updatedName);
        selectedEmployee.setCompany(updatedCompany);
        selectedEmployee.setEmail(updatedEmail);

        boolean isUpdated = employeeService.updateEmployee(selectedEmployee);
        loadTable();

        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION,"Employee Updated !!").show();
            loadTable();
        } else {
            new Alert(Alert.AlertType.ERROR,"Employee not Updated !!").show();
        }
    }

    @FXML
    void btnBackToDashboardOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/owner/owner_dashboard.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteEmployeeOnAction(ActionEvent event) {
        EmployeeDto selectedEmployee = tblEmployees.getSelectionModel().getSelectedItem();

        if (selectedEmployee == null) {
            new Alert(Alert.AlertType.ERROR,"No employee selected for deletion.").show();
            return;
        }
        String employeeId = String.valueOf(selectedEmployee.getEmpId());
        employeeService.deleteEmployee(employeeId);

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
            new Alert(Alert.AlertType.WARNING,"Please enter an Employee ID.").show();
            return;
        }

        EmployeeDto employeeDto = employeeService.search(searchId);

        if (employeeDto != null) {
           txtId.setText(employeeDto.getEmpId().toString());
           txtName.setText(employeeDto.getName());
           txtEmail.setText(employeeDto.getEmail());
           txtCompany.setText(employeeDto.getCompany());
        } else {
            new Alert(Alert.AlertType.WARNING,"No employee found with ID.").show();
        }
    }


    public void btnClearOnAction(ActionEvent actionEvent) {
        txtId.setText(null);
        txtName.setText(null);
        txtEmail.setText(null);
        txtCompany.setText(null);
    }
}

