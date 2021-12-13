package by.alex.busbooking.mapper;

import by.alex.busbooking.dto.TripDTO;
import by.alex.busbooking.entity.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {RouteMapper.class, BusMapper.class, DriverMapper.class})
public abstract class TripMapper {
    @Mapping(target = "routeDTO", source = "route")
    @Mapping(target = "busDTO", source = "bus")
    @Mapping(target = "driverDTO", source = "driver")
    public abstract TripDTO tripToTripDTO(Trip trip);

    @Mapping(target = "route", source = "routeDTO")
    @Mapping(target = "bus", source = "busDTO")
    @Mapping(target = "driver", source = "driverDTO")
    public abstract Trip tripDTOtoTrip(TripDTO tripDTO);

    public abstract List<TripDTO> tripToTripDTO(List<Trip> trips);
}
