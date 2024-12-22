create table if not exists USERS(
    id int not null AUTO_INCREMENT,
    email varchar(255) not null,
    password varchar(255) not null,
    name varchar(255) not null,
    active tinyint(1) default 1,
    created_at datetime DEFAULT current_timestamp,
    PRIMARY KEY (id)
);

create table if not exists ORDERS(
    id int not null AUTO_INCREMENT,
    user_id int,
    created_at datetime DEFAULT current_timestamp,
    PRIMARY KEY (id)
);

ALTER TABLE USERS
ADD CONSTRAINT u_users_email UNIQUE (id);

ALTER TABLE ORDERS
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES USERS (id);