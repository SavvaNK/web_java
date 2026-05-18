<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
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
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" role="alert">${errorMessage}</div>
        </c:if>
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
                            <td width="20"><a href="<c:url value='/editorder?id=${order.id}' />" role="button" class="btn btn-outline-primary"><img alt="Редактировать" src="images/icon-edit.svg" width="18"></a></td>
                            <td width="20"><a href="<c:url value='/deleteorder?id=${order.id}' />" role="button" class="btn btn-outline-primary"><img alt="Удалить" src="images/icon-delete.svg" width="18" onclick="return confirm('Удалить заказ с кодом: ${order.id}?')"></a></td>
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
