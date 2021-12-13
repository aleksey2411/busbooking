package by.alex.busbooking.mapper;

import by.alex.busbooking.dto.RouteBusStopDTO;
import by.alex.busbooking.entity.RouteBusStop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {BusStopMapper.class, RouteMapper.class})
public abstract class RouteBusStopMapper {
    @Mapping(target = "busStopDTO", source = "busStop")
    @Mapping(target = "routeDTO", source = "route")
    public abstract RouteBusStopDTO routeBusStopToRouteBusStopDTO(RouteBusStop routeBusStop);

    @Mapping(target = "busStop", source = "busStopDTO")
    @Mapping(target = "route", source = "routeDTO")
    public abstract RouteBusStop routeBusStopDTOtoRouteBusStop(RouteBusStopDTO routeBusStopDTO);

    public abstract List<RouteBusStopDTO> routeBusStopToRouteBusStopDTO(List<RouteBusStop> routeBusStops);
}
