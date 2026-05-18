<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Товары в заказах</title>
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
                <h3>Список товаров в заказах</h3>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Код</th>
                        <th scope="col">Продукт</th>
                        <th scope="col">Количество</th>
                        <th scope="col">Редактировать</th>
                        <th scope="col">Удалить</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="detail" items="${details}">
                        <tr>
                            <td>${detail.id}</td>
                            <td>${detail.productName}</td>
                            <td>${detail.count}</td>
                            <td width="20"><a href="<c:url value='/editorderdetails?id=${detail.id}' />" role="button" class="btn btn-outline-primary"><img alt="Редактировать" src="images/icon-edit.svg" width="18"></a></td>
                            <td width="20"><a href="<c:url value='/deleteorderdetails?id=${detail.id}' />" role="button" class="btn btn-outline-primary"><img alt="Удалить" src="images/icon-delete.svg" width="18" onclick="return confirm('Удалить товар в заказе с кодом: ${detail.id}?')"></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="col-lg-4 border px-4 py-3">
                <form method="POST" action="">
                    <h3>Новый товар в заказе</h3>
                    <div class="mb-3 row">
                        <label for="product" class="col-sm-4 col-form-label">Продукт</label>
                        <div class="col-sm-8">
                            <select name="product" class="form-control" id="product">
                                <option>Выберите продукт</option>
                                <c:forEach var="product" items="${products}">
                                    <option value="${product.id}">${product.productName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="count" class="col-sm-4 col-form-label">Количество</label>
                        <div class="col-sm-8"><input type="number" name="count" class="form-control" id="count" /></div>
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
