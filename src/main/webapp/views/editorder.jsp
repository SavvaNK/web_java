<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Редактирование заказа</title>
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
                <h3>Список заказов</h3>
                <table class="table"><thead><tr><th>Код</th><th>Номер</th><th>Дата</th><th>Статус</th><th>Сумма</th></tr></thead><tbody>
                <c:forEach var="order" items="${orders}"><tr><td>${order.id}</td><td>${order.numberOrder}</td><td>${order.orderDate}</td><td>${order.status}</td><td>${order.totalOrderAmount}</td></tr></c:forEach>
                </tbody></table>
            </div>
            <div class="col-lg-5 border px-4 py-3">
                <form method="POST" action="<c:url value='/editorder?id=${orderEdit.id}' />">
                    <h3>Редактировать заказ</h3>
                    <div class="mb-3 row"><label class="col-sm-4 col-form-label">Код</label><div class="col-sm-8"><input type="text" class="form-control" readonly value="${orderEdit.id}" /></div></div>
                    <div class="mb-3 row"><label for="numberOrder" class="col-sm-4 col-form-label">Номер</label><div class="col-sm-8"><input type="text" name="numberOrder" class="form-control" id="numberOrder" value="${orderEdit.numberOrder}" /></div></div>
                    <div class="mb-3 row"><label for="orderDate" class="col-sm-4 col-form-label">Дата</label><div class="col-sm-8"><input type="date" name="orderDate" class="form-control" id="orderDate" value="${orderEdit.orderDate}" /></div></div>
                    <div class="mb-3 row"><label for="status" class="col-sm-4 col-form-label">Статус</label><div class="col-sm-8"><input type="text" name="status" class="form-control" id="status" value="${orderEdit.status}" /></div></div>
                    <div class="mb-3 row"><label for="total" class="col-sm-4 col-form-label">Сумма</label><div class="col-sm-8"><input type="text" name="total" class="form-control" id="total" value="${orderEdit.totalOrderAmount}" /></div></div>
                    <div class="mb-3 row"><label for="detail" class="col-sm-4 col-form-label">Товар</label><div class="col-sm-8"><select name="detail" class="form-control" id="detail"><c:forEach var="detail" items="${details}"><option value="${detail.id}" ${detail.id == orderEdit.orderDetailId ? 'selected' : ''}>${detail.productName}</option></c:forEach></select></div></div>
                    <button type="submit" class="btn btn-primary">Редактировать</button>
                    <a href="<c:url value='/order' />" role="button" class="btn btn-secondary">Отменить</a>
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
