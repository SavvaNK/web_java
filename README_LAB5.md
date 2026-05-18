# Лабораторная работа 5

Добавлено подключение веб-приложения к PostgreSQL для варианта 26 «Информационная подсистема интернет-магазина».

## Что добавлено

- `src/main/java/dao/ConnectionProperty.java`
- `src/main/java/dao/ConnectionBuilder.java`
- `src/main/java/dao/DbConnectionBuilder.java`
- `src/main/java/dao/RepositoryDAO.java`
- `src/main/java/dao/CategoryDbDAO.java`
- `src/main/java/dao/ProductDbDAO.java`
- `src/main/java/dao/OrderDetailsDbDAO.java`
- `src/main/java/dao/OrderDbDAO.java`
- `src/main/java/exception/DAOException.java`
- `src/main/resources/config/config.properties`
- `database/init_internet_store.sql`

## Настройка базы данных

1. Создать базу и таблицы скриптом `database/init_internet_store.sql`.
2. Проверить логин, пароль и порт PostgreSQL в файле:

```text
src/main/resources/config/config.properties
```

По умолчанию:

```properties
db.url=jdbc:postgresql://localhost:5432/internet_store
db.login=postgres
db.password=postgres
```

Если у PostgreSQL другой пароль или порт, нужно изменить эти значения.

## Проверка

После запуска Tomcat открыть:

- `http://localhost:8080/internet_store/product`
- `http://localhost:8080/internet_store/category`
- `http://localhost:8080/internet_store/order`
- `http://localhost:8080/internet_store/order-details`

Данные на страницах загружаются из таблиц PostgreSQL через DAO.
