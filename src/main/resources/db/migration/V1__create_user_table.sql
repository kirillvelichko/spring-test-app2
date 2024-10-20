create table "user"
(
    id         bigint primary key generated always as identity,
    first_name varchar   not null,
    last_name  varchar   not null,
    created_at timestamp not null,
    updated_at timestamp not null
);

insert into "user" (first_name, last_name, created_at, updated_at)
values ('Ivan', 'Ivanov', current_timestamp, current_timestamp);