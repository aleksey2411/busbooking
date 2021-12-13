package by.alex.busbooking.mapper;

import by.alex.busbooking.dto.BusDTO;
import by.alex.busbooking.entity.Bus;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class BusMapper {
    public abstract BusDTO busToBusDTO(Bus bus);

    public abstract Bus busDTOtoBus(BusDTO busDTO);

    public abstract List<BusDTO> busToBusDTO(List<Bus> buses);
}

