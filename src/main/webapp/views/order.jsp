<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Category"%>
<%@ page import="domain.Product"%>
<%@ page import="domain.OrderDetails"%>
<%@ page import="domain.Order"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.time.LocalDate"%>
<%
Category c1 = new Category(1L, "Электроника", "Смартфоны, ноутбуки и аксессуары");
Category c2 = new Category(2L, "Бытовая техника", "Техника для дома и кухни");
Product p1 = new Product(1L, "Ноутбук Lenovo IdeaPad", "Ноутбук для учебы и работы", new BigDecimal("89990.00"), 1L, c1);
Product p2 = new Product(2L, "Смартфон Samsung Galaxy", "Смартфон с большим экраном", new BigDecimal("54990.00"), 1L, c1);
Product p3 = new Product(3L, "Кофемашина DeLonghi", "Автоматическая кофемашина", new BigDecimal("39990.00"), 2L, c2);
OrderDetails od1 = new OrderDetails(1L, 1L, p1, 1);
OrderDetails od2 = new OrderDetails(2L, 2L, p2, 2);
OrderDetails od3 = new OrderDetails(3L, 3L, p3, 1);
OrderDetails[] details = new OrderDetails[]{od1, od2, od3};
pageContext.setAttribute("details", details);

Order o1 = new Order(1L, "ORD-1001", LocalDate.of(2026, 5, 12), "Новый", new BigDecimal("89990.00"), 1L, od1);
Order o2 = new Order(2L, "ORD-1002", LocalDate.of(2026, 5, 13), "В обработке", new BigDecimal("109980.00"), 2L, od2);
Order o3 = new Order(3L, "ORD-1003", LocalDate.of(2026, 5, 14), "Доставлен", new BigDecimal("39990.00"), 3L, od3);
Order[] orders = new Order[]{o1, o2, o3};
pageContext.setAttribute("orders", orders);
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Заказы</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container-fluid p-0 min-vh-100 d-flex flex-column">
    <jsp:include page="/views/header.jsp" />

    <main class="container-fluid flex-grow-1 py-4">
        <div class="row justify-content-start g-3">
            <div class="col-lg-8 border bg-light px-4 py-3">
                <h3>Список заказов</h3>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Код</th>
                        <th scope="col">Номер заказа</th>
                        <th scope="col">Дата</th>
                        <th scope="col">Статус</th>
                        <th scope="col">Сумма</th>
                        <th scope="col">Товар</th>
                        <th scope="col">Редактировать</th>
                        <th scope="col">Удалить</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.numberOrder}</td>
                            <td>${order.orderDate}</td>
                            <td>${order.status}</td>
                            <td>${order.totalOrderAmount}</td>
                            <td>${order.orderDetails.productName}</td>
                            <td width="20"><a href="#" role="button" class="btn btn-outline-primary"><img alt="Редактировать" src="images/icon-edit.svg" width="18"></a></td>
                            <td width="20"><a href="#" role="button" class="btn btn-outline-primary"><img alt="Удалить" src="images/icon-delete.svg" width="18"></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="col-lg-4 border px-4 py-3">
                <form method="POST" action="">
                    <h3>Новый заказ</h3>
                    <div class="mb-3 row">
                        <label for="numberOrder" class="col-sm-4 col-form-label">Номер</label>
                        <div class="col-sm-8"><input type="text" name="numberOrder" class="form-control" id="numberOrder" /></div>
                    </div>
                    <div class="mb-3 row">
                        <label for="orderDate" class="col-sm-4 col-form-label">Дата</label>
                        <div class="col-sm-8"><input type="date" name="orderDate" class="form-control" id="orderDate" /></div>
                    </div>
                    <div class="mb-3 row">
                        <label for="status" class="col-sm-4 col-form-label">Статус</label>
                        <div class="col-sm-8"><input type="text" name="status" class="form-control" id="status" /></div>
                    </div>
                    <div class="mb-3 row">
                        <label for="total" class="col-sm-4 col-form-label">Сумма</label>
                        <div class="col-sm-8"><input type="text" name="total" class="form-control" id="total" /></div>
                    </div>
                    <div class="mb-3 row">
                        <label for="detail" class="col-sm-4 col-form-label">Товар</label>
                        <div class="col-sm-8">
                            <select name="detail" class="form-control" id="detail">
                                <option>Выберите товар</option>
                                <c:forEach var="detail" items="${details}">
                                    <option value="${detail.id}">${detail.productName}</option>
                                </c:forEach>
                            </select>
                        </div>
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
