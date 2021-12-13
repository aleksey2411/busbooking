package by.alex.busbooking.service.impl;

import by.alex.busbooking.service.DriverService;
import by.alex.busbooking.dto.DriverDTO;
import by.alex.busbooking.entity.Driver;
import by.alex.busbooking.mapper.DriverMapper;
import by.alex.busbooking.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    public DriverDTO getDriver(int id) {
        Driver driver = driverRepository.getById(id);
        return driverMapper.driverToDriverDTO(driver);
    }

    @Override
    public DriverDTO addDriver(DriverDTO driverDTO) {
        Driver driver = driverMapper.driverDTOtoDriver(driverDTO);
        Driver savedDriver = driverRepository.save(driver);
        return driverMapper.driverToDriverDTO(savedDriver);
    }

    @Override
    public List<DriverDTO> getDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        return driverMapper.driverToDriverDTO(drivers);
    }

    @Override
    public void deleteDriver(int id) {
        driverRepository.deleteById(id);
    }
}
