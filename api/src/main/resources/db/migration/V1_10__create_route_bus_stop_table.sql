create table if not exists `route_bus_stop`
(
    route_bus_stop_id                  int               not null auto_increment,
    bus_stop_id                        int               not null,
    route_id                           int               not null,
    time_to_bus_stop_in_minutes        int               not null,

    primary key (route_bus_stop_id)
);

alter table `route_bus_stop`
    add constraint unique key ak_route_bus_stop_bus_stop_id_route_id (bus_stop_id, route_id);

alter table `route_bus_stop`
    add constraint route_bus_stop_check_time_to_bus_stop_in_minutes check(time_to_bus_stop_in_minutes>0);

alter table `route_bus_stop`
    add constraint foreign key fk_route_bus_stop_bus_stop (bus_stop_id)
    references `bus_stop` (bus_stop_id);

alter table `route_bus_stop`
    add constraint foreign key fk_route_bus_stop_route (route_id)
    references `route` (route_id);