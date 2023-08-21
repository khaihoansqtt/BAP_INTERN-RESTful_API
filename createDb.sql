create database bapInter_restfulApi;
use bapInter_restfulApi;
create table users(
	user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(255),
    description VARCHAR(255),
    status INT,
    delete_flag INT(1),
    created_at DATETIME NOT NULL DEFAULT NOW(),
    updated_at DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW());
    
INSERT INTO users VALUES (1, 'khai@gmail.com', 'password', 'khai', 'quang nam', '0321312321', 'hi every one', 1, 0, now(), now()),
(2, 'hoan@gmail.com', 'password', 'hoan', 'da nang', '09312423412', 'hi every one', 1, 0, now(), now()),
(3, 'thinh@gmail.com', 'password', 'thinh', 'quang nam', '043297895', 'hi every one', 1, 0, now(), now()),
(4, 'trang@gmail.com', 'password', 'trang', 'hue', '09768623423', 'hi every one', 1, 0, now(), now()),
(5, 'hung@gmail.com', 'password', 'hung', 'da nang', '05234789324', 'hi every one', 1, 0, now(), now()),
(6, 'trien@gmail.com', 'password', 'trien', 'quang nam', '08767234234', 'hi every one', 1, 0, now(), now()),
(7, 'KHai@gmail.com', 'password', 'KHai', 'quang nam', '08767234234', 'hi every one', 1, 0, now(), now());

select * from users where name like '%KH%';