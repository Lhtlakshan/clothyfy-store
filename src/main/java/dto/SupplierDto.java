package dto;

import entity.SupplierEntity;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto {
    private Integer supplierId;
    private String name;
    private String company;
    private String email;
    private String product;

}
