create table author_entity
(
    id          int8 not null,
    created_at  timestamp,
    modified_at timestamp,
    bio         varchar(255),
    name        varchar(255),
    primary key (id)
);
create table cart_entity
(
    id          int8 not null,
    created_at  timestamp,
    modified_at timestamp,
    primary key (id)
);
create table genre_entity
(
    id          int8 not null,
    created_at  timestamp,
    modified_at timestamp,
    description varchar(255),
    name        varchar(255),
    primary key (id)
);
create table order_entity
(
    id           int8 not null,
    created_at   timestamp,
    modified_at  timestamp,
    address      varchar(255),
    name         varchar(255),
    order_status varchar(255),
    primary key (id)
);
create table product
(
    id          int8 not null,
    created_at  timestamp,
    modified_at timestamp,
    description varchar(512),
    image       varchar(255),
    preview_file_name varchar(255),
    name        varchar(255),
    price       numeric(19, 2),
    stock       int8,
    author_id   int8,
    genre_id    int8,
    primary key (id)
);
create table r_cart_products
(
    cart_id    int8 not null,
    product_id int8 not null
);
create table r_order_products
(
    order_id   int8 not null,
    product_id int8 not null
);
create sequence hibernate_sequence start 1000 increment 1;
alter table if exists product add constraint FKb2n4pu7ljn5s8p8qmcvsoj8ph foreign key (author_id) references author_entity;
alter table if exists product add constraint FK3ogn6hkrvoueckt3cuyee551s foreign key (genre_id) references genre_entity;
alter table if exists r_cart_products add constraint FKpyy0o5m88mwxa8vm4ukp1xa04 foreign key (product_id) references product;
alter table if exists r_cart_products add constraint FK4359egcfx1hsfy8srkf4o6fci foreign key (cart_id) references cart_entity;
alter table if exists r_order_products add constraint FK3e4pg1i3j3qxsrhjydrnxl8qw foreign key (product_id) references product;
alter table if exists r_order_products add constraint FK9hw9hi25d65hysk3qp2cpqsbp foreign key (order_id) references order_entity;