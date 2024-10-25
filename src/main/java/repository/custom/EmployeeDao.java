package repository.custom;

import entity.EmployeeEntity;
import javafx.collections.ObservableList;
import repository.CrudDao;

public interface EmployeeDao extends CrudDao<EmployeeEntity> {
    boolean save(EmployeeEntity employeeEntity);
    ObservableList<EmployeeEntity> getAll();
    boolean update(EmployeeEntity employeeEntity);
    boolean delete(String id);
    EmployeeEntity search(String id);
}
