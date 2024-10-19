package dto;

import java.time.LocalDate;
import java.util.List;

public class OrderDto {
    private Integer orderId;
    private List<String> productPurchased;
    private Double totalCost;
    private String paymentType;
    private List<OrderDto> orderDetails;
}
