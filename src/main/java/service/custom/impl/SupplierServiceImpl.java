package service.custom.impl;

import dto.SupplierDto;
import entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.SupplierDao;
import service.custom.SupplierService;
import util.DaoType;

import java.util.List;
import java.util.stream.Collectors;

public class SupplierServiceImpl implements SupplierService {

    SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);

    @Override
    public boolean addSupplier(SupplierDto supplierDto) {
        SupplierEntity entity = new ModelMapper().map(supplierDto, SupplierEntity.class);
        if (supplierDao.save(entity)) {
            System.out.println("Service + " + entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ObservableList<SupplierDto> getAllSuppliers() {
        ObservableList<SupplierEntity> supplierEntityObservableList = supplierDao.getAll();
        List<SupplierDto> suppliers = supplierEntityObservableList.stream()
                .map(entity -> {
                    System.out.println("Mapping entity: " + entity);
                    return new ModelMapper().map(entity, SupplierDto.class);
                })
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(suppliers);
    }


    @Override
    public boolean updateSupplier(SupplierDto supplierDto) {
        SupplierEntity entity = new ModelMapper().map(supplierDto, SupplierEntity.class);
        return supplierDao.update(entity);
    }

    @Override
    public void deleteSupplier(String id) {
        boolean isDeleted = supplierDao.delete(id);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier with ID " + id + " deleted successfully.").show();
        } else {
            System.out.println("Failed to delete supplier with ID " + id + ". It may not exist.");
        }
    }

    @Override
    public SupplierDto search(String id) {
        SupplierEntity supplierEntity = supplierDao.search(id);

        if (supplierEntity != null) {
            return new ModelMapper().map(supplierEntity, SupplierDto.class);
        }else {
            return null;
        }
    }
}
