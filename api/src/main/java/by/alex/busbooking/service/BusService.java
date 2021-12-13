package by.alex.busbooking.service;

import by.alex.busbooking.dto.BusDTO;

import java.util.List;

public interface BusService {
    BusDTO getBus(int id);
    BusDTO addBus(BusDTO busDTO);
    List<BusDTO> getBuses();
    void deleteBus(int id);
}
