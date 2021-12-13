package by.alex.busbooking.mapper;

import by.alex.busbooking.entity.BusStop;
import by.alex.busbooking.dto.BusStopDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {CityMapper.class})
public abstract class BusStopMapper {
    @Mapping(target = "cityDTO", source = "city")
    public abstract BusStopDTO busStopToBusStopDTO(BusStop busStop);

    @Mapping(target = "city", source = "cityDTO")
    public abstract BusStop busStopDTOtoBusStop(BusStopDTO busStopDTO);

    public abstract List<BusStopDTO> busStopToBusStopDTO(List<BusStop> busStops);
}
