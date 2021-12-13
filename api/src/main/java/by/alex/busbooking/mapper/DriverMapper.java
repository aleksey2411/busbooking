package by.alex.busbooking.mapper;

import by.alex.busbooking.entity.Driver;
import by.alex.busbooking.dto.DriverDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class DriverMapper {
    public abstract DriverDTO driverToDriverDTO(Driver driver);

    public abstract Driver driverDTOtoDriver(DriverDTO driverDTO);

    public abstract List<DriverDTO> driverToDriverDTO(List<Driver> drivers);
}
