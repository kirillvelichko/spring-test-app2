create table app_config
(
    id          bigint primary key generated always as identity,
    name        varchar not null,
    value       varchar not null,
    description varchar not null
);

create unique index app_config_name_idx on app_config (name);

insert into app_config (name, value, description)
values ('signature', 'Warmest regards.', 'Signature to hello message');