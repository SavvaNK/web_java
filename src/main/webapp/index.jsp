<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <title>Интернет-магазин</title>
</head>
<body>
<div class="container-fluid p-0 min-vh-100 d-flex flex-column">
    <jsp:include page="/views/header.jsp"/>

    <main class="container flex-grow-1 py-5">
        <section class="text-center mb-4">
            <h2>Функции системы</h2>
            <p class="text-muted">Информационная подсистема интернет-магазина</p>
        </section>

        <div class="row g-3 justify-content-center">
            <div class="col-md-6 col-lg-3">
                <a class="function-card list-group-item list-group-item-primary" href="ProductServlet">
                    Продукты
                </a>
            </div>
            <div class="col-md-6 col-lg-3">
                <a class="function-card list-group-item list-group-item-info" href="CategoryServlet">
                    Категории
                </a>
            </div>
            <div class="col-md-6 col-lg-3">
                <a class="function-card list-group-item list-group-item-warning" href="OrderServlet">
                    Заказы
                </a>
            </div>
            <div class="col-md-6 col-lg-3">
                <a class="function-card list-group-item list-group-item-success" href="OrderDetailsServlet">
                    Товары в заказе
                </a>
            </div>
        </div>
    </main>

    <jsp:include page="/views/footer.jsp"/>
</div>

<script src="js/jquery-3.6.4.js"></script>
<script defer src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
