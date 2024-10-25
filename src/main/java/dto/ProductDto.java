package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer productId;
    private Integer supplierId;
    private String name;
    private String category;
    private Double size;
    private Double price;
    private Integer quantityOnHand;

}
