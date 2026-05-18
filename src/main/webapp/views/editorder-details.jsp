<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Редактирование товара в заказе</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container-fluid p-0 min-vh-100 d-flex flex-column">
    <jsp:include page="/views/header.jsp" />
    <main class="container-fluid flex-grow-1 py-4">
        <c:if test="${not empty errorMessage}"><div class="alert alert-danger" role="alert">${errorMessage}</div></c:if>
        <div class="row justify-content-start g-3">
            <div class="col-lg-7 border bg-light px-4 py-3">
                <h3>Список товаров в заказах</h3>
                <table class="table"><thead><tr><th>Код</th><th>Продукт</th><th>Количество</th></tr></thead><tbody>
                <c:forEach var="detail" items="${details}"><tr><td>${detail.id}</td><td>${detail.productName}</td><td>${detail.count}</td></tr></c:forEach>
                </tbody></table>
            </div>
            <div class="col-lg-5 border px-4 py-3">
                <form method="POST" action="<c:url value='/editorderdetails?id=${detailEdit.id}' />">
                    <h3>Редактировать товар в заказе</h3>
                    <div class="mb-3 row"><label class="col-sm-4 col-form-label">Код</label><div class="col-sm-8"><input type="text" class="form-control" readonly value="${detailEdit.id}" /></div></div>
                    <div class="mb-3 row"><label for="product" class="col-sm-4 col-form-label">Продукт</label><div class="col-sm-8"><select name="product" class="form-control" id="product"><c:forEach var="product" items="${products}"><option value="${product.id}" ${product.id == detailEdit.productId ? 'selected' : ''}>${product.productName}</option></c:forEach></select></div></div>
                    <div class="mb-3 row"><label for="count" class="col-sm-4 col-form-label">Количество</label><div class="col-sm-8"><input type="number" name="count" class="form-control" id="count" value="${detailEdit.count}" /></div></div>
                    <button type="submit" class="btn btn-primary">Редактировать</button>
                    <a href="<c:url value='/order-details' />" role="button" class="btn btn-secondary">Отменить</a>
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
