create table if not exists `city`
(
    city_id               int               not null auto_increment,
    `name`                varchar(255)      not null,

    primary key (city_id)
);

alter table `city`
    add constraint unique key ak_city_name (`name`);

