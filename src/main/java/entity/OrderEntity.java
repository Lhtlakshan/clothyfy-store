package entity;

import java.util.List;

public class OrderEntity {
    private Integer orderId;
    private List<String> productPurchased;
    private Double totalCost;
    private String paymentType;
    private List<OrderEntity> orderDetails;
}
