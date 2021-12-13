create table if not exists `bus`
(
    bus_id                  int               not null auto_increment,
    `number`                varchar(255)      not null,
    mark                    varchar(255)      not null,
    model                   varchar(255)      not null,
    color                   varchar(255)      not null,
    number_of_seats         int               not null,

    primary key (bus_id)
);

alter table `bus`
    add constraint unique key ak_bus_number (`number`);

alter table `bus`
    add constraint bus_check_number_of_seats check(number_of_seats>0);