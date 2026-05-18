package domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Класс данных о заказе интернет-магазина.
 */
public class Order {
    // Идентификатор заказа
    private Long id;

    // Внешний ключ - ссылка на сущность OrderDetails
    private Long orderDetailId;

    // Номер заказа
    private String numberOrder;

    // Дата заказа
    private LocalDate orderDate;

    // Статус заказа
    private String status;

    // Сумма заказа
    private BigDecimal totalOrderAmount;

    // Навигационное свойство - ссылка на товар в заказе
    private OrderDetails orderDetails;

    public Order() {
    }

    public Order(String numberOrder, LocalDate orderDate, String status, BigDecimal totalOrderAmount, OrderDetails orderDetails) {
        this.numberOrder = numberOrder;
        this.orderDate = orderDate;
        this.status = status;
        this.totalOrderAmount = totalOrderAmount;
        this.orderDetails = orderDetails;
        this.orderDetailId = orderDetails != null ? orderDetails.getId() : null;
    }

    public Order(String numberOrder, LocalDate orderDate, String status, BigDecimal totalOrderAmount,
                 Long orderDetailId, OrderDetails orderDetails) {
        this.numberOrder = numberOrder;
        this.orderDate = orderDate;
        this.status = status;
        this.totalOrderAmount = totalOrderAmount;
        this.orderDetailId = orderDetailId;
        this.orderDetails = orderDetails;
    }

    public Order(Long id, String numberOrder, LocalDate orderDate, String status, BigDecimal totalOrderAmount,
                 Long orderDetailId, OrderDetails orderDetails) {
        this.id = id;
        this.numberOrder = numberOrder;
        this.orderDate = orderDate;
        this.status = status;
        this.totalOrderAmount = totalOrderAmount;
        this.orderDetailId = orderDetailId;
        this.orderDetails = orderDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Order {" +
                "id = " + id +
                ", orderDetailId = " + orderDetailId +
                ", numberOrder = '" + numberOrder + '\'' +
                ", orderDate = " + orderDate +
                ", status = '" + status + '\'' +
                ", totalOrderAmount = " + totalOrderAmount +
                ", orderDetails = " + orderDetails +
                '}';
    }
}
