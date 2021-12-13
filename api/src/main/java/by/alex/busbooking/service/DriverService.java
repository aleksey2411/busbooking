package by.alex.busbooking.service;

import by.alex.busbooking.dto.DriverDTO;

import java.util.List;

public interface DriverService {
    DriverDTO getDriver(int id);

    DriverDTO addDriver(DriverDTO driverDTO);

    List<DriverDTO> getDrivers();

    void deleteDriver(int id);
}
