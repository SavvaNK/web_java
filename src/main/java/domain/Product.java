package domain;

import java.math.BigDecimal;

/**
 * Класс данных о продукте интернет-магазина.
 */
public class Product {
    // Идентификатор продукта
    private Long id;

    // Внешний ключ - ссылка на сущность Category
    private Long categoryId;

    // Название продукта
    private String productName;

    // Описание продукта
    private String description;

    // Цена продукта
    private BigDecimal price;

    // Навигационное свойство - ссылка на категорию
    private Category category;

    public Product() {
    }

    public Product(String productName, String description, BigDecimal price, Category category) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.category = category;
        this.categoryId = category != null ? category.getId() : null;
    }

    public Product(String productName, String description, BigDecimal price, Long categoryId, Category category) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.category = category;
    }

    public Product(Long id, String productName, String description, BigDecimal price, Long categoryId, Category category) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public String getCategoryName() {
        return category != null ? category.getCategoryName() : "";
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product {" +
                "id = " + id +
                ", categoryId = " + categoryId +
                ", productName = '" + productName + '\'' +
                ", description = '" + description + '\'' +
                ", price = " + price +
                ", categoryName = '" + getCategoryName() + '\'' +
                '}';
    }
}
