package dto;

import entity.OrderEntity;

import java.time.LocalDate;
import java.util.List;

public class OrderDto {
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
