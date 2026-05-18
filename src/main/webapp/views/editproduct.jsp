<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Редактирование продукта</title>
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
                <h3>Список продуктов</h3>
                <table class="table"><thead><tr><th>Код</th><th>Продукт</th><th>Категория</th><th>Цена</th></tr></thead><tbody>
                <c:forEach var="product" items="${products}"><tr><td>${product.id}</td><td>${product.productName}</td><td>${product.categoryName}</td><td>${product.price}</td></tr></c:forEach>
                </tbody></table>
            </div>
            <div class="col-lg-5 border px-4 py-3">
                <form method="POST" action="<c:url value='/editproduct?id=${productEdit.id}' />">
                    <h3>Редактировать продукт</h3>
                    <div class="mb-3 row"><label class="col-sm-4 col-form-label">Код</label><div class="col-sm-8"><input type="text" class="form-control" readonly value="${productEdit.id}" /></div></div>
                    <div class="mb-3 row"><label for="productName" class="col-sm-4 col-form-label">Название</label><div class="col-sm-8"><input type="text" name="productName" class="form-control" id="productName" value="${productEdit.productName}" /></div></div>
                    <div class="mb-3 row"><label for="category" class="col-sm-4 col-form-label">Категория</label><div class="col-sm-8"><select name="category" class="form-control" id="category"><c:forEach var="category" items="${categories}"><option value="${category.id}" ${category.id == productEdit.categoryId ? 'selected' : ''}>${category.categoryName}</option></c:forEach></select></div></div>
                    <div class="mb-3 row"><label for="description" class="col-sm-4 col-form-label">Описание</label><div class="col-sm-8"><input type="text" name="description" class="form-control" id="description" value="${productEdit.description}" /></div></div>
                    <div class="mb-3 row"><label for="price" class="col-sm-4 col-form-label">Цена</label><div class="col-sm-8"><input type="text" name="price" class="form-control" id="price" value="${productEdit.price}" /></div></div>
                    <button type="submit" class="btn btn-primary">Редактировать</button>
                    <a href="<c:url value='/product' />" role="button" class="btn btn-secondary">Отменить</a>
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
