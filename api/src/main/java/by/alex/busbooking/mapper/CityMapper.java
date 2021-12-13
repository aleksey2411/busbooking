package by.alex.busbooking.mapper;

import by.alex.busbooking.dto.CityDTO;
import by.alex.busbooking.entity.City;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class                                                                                                                                                                                                   CityMapper {
    public abstract CityDTO cityToCityDTO(City city);

    public abstract City cityDTOtoCity(CityDTO cityDTO);

    public abstract List<CityDTO> cityToCityDTO(List<City> cities);
}
