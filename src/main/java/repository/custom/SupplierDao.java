package repository.custom;

import entity.EmployeeEntity;
import entity.SupplierEntity;
import javafx.collections.ObservableList;
import repository.CrudDao;

public interface SupplierDao extends CrudDao<SupplierEntity> {
    boolean save(SupplierEntity supplierEntity);
    ObservableList<SupplierEntity> getAll();
    boolean update(SupplierEntity supplierEntity);
    boolean delete(String id);
    SupplierEntity search(String id);
}
