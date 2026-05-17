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
<div class="container-fluid app-shell">
    <jsp:include page="/views/header.jsp" />

    <main class="container content-wrapper">
        <section class="hero-card text-center">
            <p class="section-label">Вариант 26</p>
            <h1>Информационная подсистема интернет-магазина</h1>
            <p class="lead">
                Поддержание в актуальном состоянии информации о продуктах,
                категориях, заказах и составе заказов.
            </p>
        </section>

        <section class="list-group text-center system-functions">
            <h2>Функции системы</h2>
            <div class="row g-3 mt-3">
                <div class="col-md-6 col-lg-3">
                    <a class="list-group-item list-group-item-primary function-link" href="products.jsp">
                        <span class="function-icon">🛒</span>
                        <span>Продукты</span>
                        <small>Product</small>
                    </a>
                </div>
                <div class="col-md-6 col-lg-3">
                    <a class="list-group-item list-group-item-info function-link" href="categories.jsp">
                        <span class="function-icon">📂</span>
                        <span>Категории</span>
                        <small>Category</small>
                    </a>
                </div>
                <div class="col-md-6 col-lg-3">
                    <a class="list-group-item list-group-item-warning function-link" href="orders.jsp">
                        <span class="function-icon">📦</span>
                        <span>Заказы</span>
                        <small>Order</small>
                    </a>
                </div>
                <div class="col-md-6 col-lg-3">
                    <a class="list-group-item list-group-item-success function-link" href="order-details.jsp">
                        <span class="function-icon">🧾</span>
                        <span>Товары в заказе</span>
                        <small>OrderDetails</small>
                    </a>
                </div>
            </div>
        </section>
    </main>

    <jsp:include page="/views/footer.jsp" />
</div>
<script src="js/jquery-3.6.4.js"></script>
<script defer src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
