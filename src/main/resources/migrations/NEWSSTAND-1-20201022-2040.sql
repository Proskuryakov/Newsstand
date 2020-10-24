create table printed_matter_types
(
    id   serial primary key  not null,
    name varchar(100) unique not null
);

create table printed_matters
(
    id               serial primary key not null,
    name             varchar(255)       not null,
    price            numeric            not null,
    type_id          integer            not null,
    author           varchar(255) default null,
    publishing_house varchar(250) default null,
    page_count       integer      default null,
    publishing_date  date         default null,
    number           integer      default null,

    constraint printed_matters_printed_matter_types_type_id_fk
        foreign key (type_id)
            references printed_matter_types (id) on delete cascade
);

create table basket
(
    id                   serial primary key not null,
    printed_matter_name  varchar(255)       not null,
    printed_matter_price numeric            not null
);

create table action_types
(
    id   serial primary key  not null,
    name varchar(100) unique not null
);

create table event_log
(
    id          serial primary key not null,
    action_id   integer            not null,
    quantity    integer            not null,
    basket_id   integer            not null,
    action_date timestamp          not null,
    price_sum   money              not null,

    constraint event_log_action_types_action_id_fk
        foreign key (action_id)
            references action_types (id) on delete cascade,

    constraint event_log_basket_basket_id_fk
        foreign key (basket_id)
            references basket (id)
);