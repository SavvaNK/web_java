package domain;

/**
 * Класс данных о товаре в заказе.
 */
public class OrderDetails {
    // Идентификатор записи о товаре в заказе
    private Long id;

    // Внешний ключ - ссылка на сущность Product
    private Long productId;

    // Количество товаров
    private Integer count;

    // Навигационное свойство - ссылка на продукт
    private Product product;

    public OrderDetails() {
    }

    public OrderDetails(Product product, Integer count) {
        this.product = product;
        this.productId = product != null ? product.getId() : null;
        this.count = count;
    }

    public OrderDetails(Long productId, Product product, Integer count) {
        this.productId = productId;
        this.product = product;
        this.count = count;
    }

    public OrderDetails(Long id, Long productId, Product product, Integer count) {
        this.id = id;
        this.productId = productId;
        this.product = product;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public String getProductName() {
        return product != null ? product.getProductName() : "";
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderDetails {" +
                "id = " + id +
                ", productId = " + productId +
                ", productName = '" + getProductName() + '\'' +
                ", count = " + count +
                '}';
    }
}
