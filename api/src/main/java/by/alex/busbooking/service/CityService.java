package by.alex.busbooking.service;

import by.alex.busbooking.dto.CityDTO;

import java.util.List;

public interface CityService {
    CityDTO getCity(int id);

    CityDTO addCity(CityDTO cityDTO);

    List<CityDTO> getCities();

    void deleteCity(int id);
}
