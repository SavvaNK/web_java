package domain;

/**
 * Класс данных о категории товара.
 */
public class Category {
    // Идентификатор категории
    private Long id;

    // Название категории
    private String categoryName;

    // Описание категории
    private String description;

    public Category() {
    }

    public Category(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    public Category(Long id, String categoryName, String description) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category {" +
                "id = " + id +
                ", categoryName = '" + categoryName + '\'' +
                ", description = '" + description + '\'' +
                '}';
    }
}
