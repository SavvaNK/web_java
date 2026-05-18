# Лабораторная работа 6

Тема: создание новых записей в базе данных PostgreSQL.

В проект добавлена обработка `POST`-запросов в сервлетах:

- `CategoryServlet` — добавление категории;
- `ProductServlet` — добавление продукта;
- `OrderDetailsServlet` — добавление товара в заказ;
- `OrderServlet` — добавление заказа.

Формы на JSP-страницах отправляют данные методом `POST`, сервлеты считывают параметры через `request.getParameter(...)`, создают объекты домена и вызывают метод `insert(...)` соответствующего DAO-класса.

## Проверка

Перед запуском должна быть создана база `internet_store` и выполнен скрипт:

```text
database/init_internet_store.sql
```

Настройки подключения находятся в:

```text
src/main/resources/config/config.properties
```

Основные страницы:

```text
http://localhost:8080/internet_store/category
http://localhost:8080/internet_store/product
http://localhost:8080/internet_store/order-details
http://localhost:8080/internet_store/order
```

После заполнения формы и нажатия кнопки «Добавить» новая запись появляется в соответствующей таблице PostgreSQL и в таблице на странице.

## Git

```bash
git checkout -b dev6
git add .
git commit -m "Add create operations for lab 6"
```
