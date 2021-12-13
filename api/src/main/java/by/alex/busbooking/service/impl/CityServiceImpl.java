package by.alex.busbooking.service.impl;

import by.alex.busbooking.repository.CityRepository;
import by.alex.busbooking.service.CityService;
import by.alex.busbooking.dto.CityDTO;
import by.alex.busbooking.entity.City;
import by.alex.busbooking.mapper.CityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public CityDTO getCity(int id) {
        City city = cityRepository.getById(id);
        return cityMapper.cityToCityDTO(city);
    }

    @Override
    public CityDTO addCity(CityDTO cityDTO) {
        City city = cityMapper.cityDTOtoCity(cityDTO);
        City savedCity = cityRepository.save(city);
        return cityMapper.cityToCityDTO(savedCity);
    }

    @Override
    public List<CityDTO> getCities() {
        List<City> cities = cityRepository.findAll();
        return cityMapper.cityToCityDTO(cities);
    }

    @Override
    public void deleteCity(int id) {
        cityRepository.deleteById(id);
    }
}
