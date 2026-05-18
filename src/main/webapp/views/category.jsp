<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Category"%>
<%
Category c1 = new Category(1L, "Электроника", "Смартфоны, ноутбуки и аксессуары");
Category c2 = new Category(2L, "Бытовая техника", "Техника для дома и кухни");
Category c3 = new Category(3L, "Книги", "Печатные и электронные книги");
Category c4 = new Category(4L, "Одежда", "Повседневная одежда и обувь");
Category[] categories = new Category[]{c1, c2, c3, c4};
pageContext.setAttribute("categories", categories);
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Категории</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container-fluid p-0 min-vh-100 d-flex flex-column">
    <jsp:include page="/views/header.jsp" />

    <main class="container-fluid flex-grow-1 py-4">
        <div class="row justify-content-start g-3">
            <div class="col-lg-8 border bg-light px-4 py-3">
                <h3>Список категорий</h3>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Код</th>
                        <th scope="col">Категория</th>
                        <th scope="col">Описание</th>
                        <th scope="col">Редактировать</th>
                        <th scope="col">Удалить</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="category" items="${categories}">
                        <tr>
                            <td>${category.id}</td>
                            <td>${category.categoryName}</td>
                            <td>${category.description}</td>
                            <td width="20"><a href="#" role="button" class="btn btn-outline-primary"><img alt="Редактировать" src="images/icon-edit.svg" width="18"></a></td>
                            <td width="20"><a href="#" role="button" class="btn btn-outline-primary"><img alt="Удалить" src="images/icon-delete.svg" width="18"></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="col-lg-4 border px-4 py-3">
                <form method="POST" action="">
                    <h3>Новая категория</h3>
                    <div class="mb-3 row">
                        <label for="categoryName" class="col-sm-4 col-form-label">Название</label>
                        <div class="col-sm-8"><input type="text" name="categoryName" class="form-control" id="categoryName" /></div>
                    </div>
                    <div class="mb-3 row">
                        <label for="description" class="col-sm-4 col-form-label">Описание</label>
                        <div class="col-sm-8"><input type="text" name="description" class="form-control" id="description" /></div>
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
