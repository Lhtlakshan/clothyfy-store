package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    private Integer orderId;
    private Integer employeeId;
    private String employeeName;
    private String customerName;
    private String productId;
    private String productName;
    private Double totalCost;
    private String paymentType;
    private List<OrderEntity> orderDetails;
}
