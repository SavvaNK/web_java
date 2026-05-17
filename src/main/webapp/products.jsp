<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <title>Продукты</title>
</head>
<body>
<div class="container-fluid app-shell">
    <jsp:include page="/views/header.jsp" />
    <main class="container content-wrapper">
        <section class="hero-card">
            <h1>Продукты</h1>
            <p class="lead">Справочник продуктов: код, категория, название, описание и цена.</p>
            <a class="btn btn-primary" href="index.jsp">Вернуться на главную</a>
        </section>
    </main>
    <jsp:include page="/views/footer.jsp" />
</div>
<script src="js/jquery-3.6.4.js"></script>
<script defer src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
