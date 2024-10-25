package service.custom;

import dto.SupplierDto;
import javafx.collections.ObservableList;
import service.SuperService;

public interface SupplierService extends SuperService {
    boolean addSupplier(SupplierDto supplierDto);
    ObservableList<SupplierDto> getAllSuppliers();
    boolean updateSupplier(SupplierDto supplierDto);
    void deleteSupplier(String id);
    SupplierDto search(String id);
}
