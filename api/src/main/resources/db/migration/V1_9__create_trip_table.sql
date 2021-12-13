create table if not exists `trip`
(
    trip_id                 int               not null auto_increment,
    departure_date_time     datetime          not null,
    arrival_date_time       datetime          not null,
    bus_id                  int               not null,
    driver_id               int               not null,
    route_id                int               not null,

    primary key (trip_id)
);

alter table `trip`
    add constraint unique key ak_trip_departure_date_time_bus_id (departure_date_time, bus_id);

alter table `trip`
    add constraint unique key ak_trip_departure_date_time_driver_id (departure_date_time, driver_id);

alter table `trip`
    add constraint foreign key fk_trip_bus (bus_id)
    references `bus` (bus_id);

alter table `trip`
    add constraint foreign key fk_trip_driver (driver_id)
    references `driver` (driver_id);

alter table `trip`
    add constraint foreign key fk_trip_route (route_id)
    references `route` (route_id);