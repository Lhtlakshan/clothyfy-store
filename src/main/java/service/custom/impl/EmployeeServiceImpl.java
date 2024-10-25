package service.custom.impl;


import dto.EmployeeDto;
import entity.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDao;
import service.custom.EmployeeService;
import util.DaoType;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);

    @Override
    public boolean addEmployee(EmployeeDto employeeDto) {

        EmployeeEntity entity = new ModelMapper().map(employeeDto, EmployeeEntity.class);
        if (employeeDao.save(entity)) {
            System.out.println("Service + " + entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ObservableList<EmployeeDto> getAllEmployees() {
        ObservableList<EmployeeEntity> customersEntityList = employeeDao.getAll();
        List<EmployeeDto> employees = customersEntityList.stream()
                .map(entity -> new ModelMapper().map(entity, EmployeeDto.class))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(employees);

    }

    @Override
    public boolean updateEmployee(EmployeeDto employeeDto) {
        EmployeeEntity entity = new ModelMapper().map(employeeDto, EmployeeEntity.class);
        return employeeDao.update(entity);
    }

    @Override
    public void deleteEmployee(String id) {
        boolean isDeleted = employeeDao.delete(id);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION,"Employee with ID " + id + " deleted successfully.").show();
        } else {
            System.out.println("Failed to delete employee with ID " + id + ". It may not exist.");
        }
    }

    @Override
    public EmployeeDto search(String id) {
        EmployeeEntity employeeEntity = employeeDao.search(id);

        if (employeeEntity != null) {
            return new ModelMapper().map(employeeEntity, EmployeeDto.class);
        }else {
            return null;
        }
    }


}