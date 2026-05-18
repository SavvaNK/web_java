# Лабораторная работа 4

Добавлены JSP-представления для варианта 26 «Информационная подсистема интернет-магазина».

## Новые/измененные страницы

- `src/main/webapp/views/product.jsp`
- `src/main/webapp/views/category.jsp`
- `src/main/webapp/views/order.jsp`
- `src/main/webapp/views/order-details.jsp`
- `src/main/webapp/index.jsp`

## Измененные сервлеты

- `ProductServlet` → `/product`
- `CategoryServlet` → `/category`
- `OrderServlet` → `/order`
- `OrderDetailsServlet` → `/order-details`

## Проверка

После запуска Tomcat открой:

- `http://localhost:8080/internet_store/product`
- `http://localhost:8080/internet_store/category`
- `http://localhost:8080/internet_store/order`
- `http://localhost:8080/internet_store/order-details`

Для работы JSTL в `pom.xml` добавлены зависимости `jakarta.servlet.jsp.jstl-api` и `jakarta.servlet.jsp.jstl`.
