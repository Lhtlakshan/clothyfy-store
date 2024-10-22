package entity;

import java.util.List;

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
