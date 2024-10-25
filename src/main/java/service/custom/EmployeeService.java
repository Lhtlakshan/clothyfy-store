package service.custom;

import dto.EmployeeDto;
import javafx.collections.ObservableList;
import service.SuperService;

public interface EmployeeService extends SuperService {
    boolean addEmployee(EmployeeDto employeeDto);
    ObservableList<EmployeeDto> getAllEmployees();
    boolean updateEmployee(EmployeeDto employeeDto);
    void deleteEmployee(String id);
    EmployeeDto search(String id);
}