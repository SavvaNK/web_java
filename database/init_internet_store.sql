-- ЛР-5. База данных для варианта 26: информационная подсистема интернет-магазина.
-- Выполнить в PostgreSQL перед запуском веб-приложения.

CREATE DATABASE internet_store;

-- После создания базы подключитесь к ней в pgAdmin или psql:
-- \c internet_store

DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_details;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;

CREATE TABLE IF NOT EXISTS categories (
    id serial NOT NULL,
    category_name character varying(100) NOT NULL,
    description character varying(255),
    CONSTRAINT categories_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS products (
    id serial NOT NULL,
    category_id integer NOT NULL,
    product_name character varying(120) NOT NULL,
    description character varying(255),
    price numeric(12, 2) NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id),
    CONSTRAINT products_category_id_fkey FOREIGN KEY (category_id)
        REFERENCES categories (id)
);

CREATE TABLE IF NOT EXISTS order_details (
    id serial NOT NULL,
    product_id integer NOT NULL,
    count integer NOT NULL,
    CONSTRAINT order_details_pkey PRIMARY KEY (id),
    CONSTRAINT order_details_product_id_fkey FOREIGN KEY (product_id)
        REFERENCES products (id)
);

CREATE TABLE IF NOT EXISTS orders (
    id serial NOT NULL,
    order_detail_id integer NOT NULL,
    number_order character varying(30) NOT NULL,
    order_date date NOT NULL,
    status character varying(40) NOT NULL,
    total_order_amount numeric(12, 2) NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT orders_order_detail_id_fkey FOREIGN KEY (order_detail_id)
        REFERENCES order_details (id)
);

INSERT INTO categories(category_name, description) VALUES
('Электроника', 'Смартфоны, ноутбуки и аксессуары'),
('Бытовая техника', 'Техника для дома и кухни'),
('Книги', 'Печатные и электронные книги'),
('Одежда', 'Повседневная одежда и обувь');

INSERT INTO products(category_id, product_name, description, price) VALUES
(1, 'Ноутбук Lenovo IdeaPad', 'Ноутбук для учебы и работы', 89990.00),
(1, 'Смартфон Samsung Galaxy', 'Смартфон с большим экраном', 54990.00),
(2, 'Кофемашина DeLonghi', 'Автоматическая кофемашина', 39990.00),
(3, 'Java EE. Руководство', 'Книга по разработке веб-приложений', 2490.00);

INSERT INTO order_details(product_id, count) VALUES
(1, 1),
(2, 2),
(3, 1),
(4, 3);

INSERT INTO orders(order_detail_id, number_order, order_date, status, total_order_amount) VALUES
(1, 'ORD-1001', '2026-05-12', 'Новый', 89990.00),
(2, 'ORD-1002', '2026-05-13', 'В обработке', 109980.00),
(3, 'ORD-1003', '2026-05-14', 'Доставлен', 39990.00),
(4, 'ORD-1004', '2026-05-15', 'Новый', 7470.00);
