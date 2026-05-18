<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Category"%>
<%@ page import="domain.Product"%>
<%@ page import="java.math.BigDecimal"%>
<%
Category c1 = new Category(1L, "Электроника", "Смартфоны, ноутбуки и аксессуары");
Category c2 = new Category(2L, "Бытовая техника", "Техника для дома и кухни");
Category c3 = new Category(3L, "Книги", "Печатные и электронные книги");
Category c4 = new Category(4L, "Одежда", "Повседневная одежда и обувь");
Category[] categories = new Category[]{c1, c2, c3, c4};
pageContext.setAttribute("categories", categories);

Product p1 = new Product(1L, "Ноутбук Lenovo IdeaPad", "Ноутбук для учебы и работы", new BigDecimal("89990.00"), 1L, c1);
Product p2 = new Product(2L, "Смартфон Samsung Galaxy", "Смартфон с большим экраном", new BigDecimal("54990.00"), 1L, c1);
Product p3 = new Product(3L, "Кофемашина DeLonghi", "Автоматическая кофемашина", new BigDecimal("39990.00"), 2L, c2);
Product p4 = new Product(4L, "Java EE. Руководство", "Книга по разработке веб-приложений", new BigDecimal("2490.00"), 3L, c3);
Product[] products = new Product[]{p1, p2, p3, p4};
pageContext.setAttribute("products", products);
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Продукты</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container-fluid p-0 min-vh-100 d-flex flex-column">
    <jsp:include page="/views/header.jsp" />

    <main class="container-fluid flex-grow-1 py-4">
        <div class="row justify-content-start g-3">
            <div class="col-lg-8 border bg-light px-4 py-3">
                <h3>Список продуктов</h3>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Код</th>
                        <th scope="col">Продукт</th>
                        <th scope="col">Категория</th>
                        <th scope="col">Описание</th>
                        <th scope="col">Цена</th>
                        <th scope="col">Редактировать</th>
                        <th scope="col">Удалить</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.id}</td>
                            <td>${product.productName}</td>
                            <td>${product.categoryName}</td>
                            <td>${product.description}</td>
                            <td>${product.price}</td>
                            <td width="20"><a href="#" role="button" class="btn btn-outline-primary"><img alt="Редактировать" src="images/icon-edit.svg" width="18"></a></td>
                            <td width="20"><a href="#" role="button" class="btn btn-outline-primary"><img alt="Удалить" src="images/icon-delete.svg" width="18"></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="col-lg-4 border px-4 py-3">
                <form method="POST" action="">
                    <h3>Новый продукт</h3>
                    <div class="mb-3 row">
                        <label for="productName" class="col-sm-4 col-form-label">Название</label>
                        <div class="col-sm-8"><input type="text" name="productName" class="form-control" id="productName" /></div>
                    </div>
                    <div class="mb-3 row">
                        <label for="category" class="col-sm-4 col-form-label">Категория</label>
                        <div class="col-sm-8">
                            <select name="category" class="form-control" id="category">
                                <option>Выберите категорию</option>
                                <c:forEach var="category" items="${categories}">
                                    <option value="${category.id}">${category.categoryName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="description" class="col-sm-4 col-form-label">Описание</label>
                        <div class="col-sm-8"><input type="text" name="description" class="form-control" id="description" /></div>
                    </div>
                    <div class="mb-3 row">
                        <label for="price" class="col-sm-4 col-form-label">Цена</label>
                        <div class="col-sm-8"><input type="text" name="price" class="form-control" id="price" /></div>
                    </div>
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </form>
            </div>
        </div>
    </main>

    <jsp:include page="/views/footer.jsp" />
</div>
<script src="js/jquery-3.6.4.js"></script>
<script defer src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
