create table if not exists `route`
(
    route_id                  int               not null auto_increment,
    arrival_city_id           int               not null,
    departure_city_id         int               not null,
    cost                      int               not null,

    primary key (route_id)
);

alter table `route`
    add constraint unique key ak_route_arrival_city_id_departure_city_id(arrival_city_id, departure_city_id);

alter table `route`
    add constraint route_check_arrival_city_id_departure_city_id check(arrival_city_id != departure_city_id);

alter table `route`
    add constraint route_check_cost check(cost>0);

alter table `route`
    add constraint foreign key fk_route_arrival_city (arrival_city_id)
    references `city` (city_id);

alter table `route`
    add constraint foreign key fk_route_departure_city (departure_city_id)
    references `city` (city_id);