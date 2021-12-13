package by.alex.busbooking.mapper;

import by.alex.busbooking.dto.RouteDTO;
import by.alex.busbooking.entity.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {CityMapper.class})
public abstract class RouteMapper {
    @Mapping(target = "arrivalCityDTO", source = "arrivalCity")
    @Mapping(target = "departureCityDTO", source = "departureCity")
    public abstract RouteDTO routeToRouteDTO(Route route);

    @Mapping(target = "arrivalCity", source = "arrivalCityDTO")
    @Mapping(target = "departureCity", source = "departureCityDTO")
    public abstract Route routeDTOtoRoute(RouteDTO routeDTO);

    public abstract List<RouteDTO> routeToRouteDTO(List<Route> routes);
}
