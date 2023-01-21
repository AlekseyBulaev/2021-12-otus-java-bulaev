create table client
(
    id           bigserial   not null primary key,
    name         varchar(500) not null
);

create table address
(
    client_id   bigint      not null references client (id),
    street      varchar(500) not null
);

create table phone
(
    client_id bigint      not null references client (id),
    num       varchar(500) not null
);