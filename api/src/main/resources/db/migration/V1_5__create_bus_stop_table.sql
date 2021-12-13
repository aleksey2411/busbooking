create table if not exists `bus_stop`
(
    bus_stop_id             int               not null auto_increment,
    city_id                 int               not null,
    `name`                  varchar(255)      not null,

    primary key (bus_stop_id)
);

alter table `bus_stop`
    add constraint unique key ak_bus_stop_name_city_id (`name`, city_id);

alter table `bus_stop`
    add constraint foreign key fk_bus_stop_city (city_id)
    references `city` (city_id);