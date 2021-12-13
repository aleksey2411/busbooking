create table if not exists `driver`
(
    driver_id                int               not null auto_increment,
    driver_license           varchar(255)      not null,
    first_name               varchar(255)      not null,
    last_name                varchar(255)      not null,

    primary key (driver_id)
);

alter table `driver`
    add constraint unique key ak_driver_driver_license (driver_license);
