package by.alex.busbooking.service.impl;

import by.alex.busbooking.dto.BusDTO;
import by.alex.busbooking.entity.Bus;
import by.alex.busbooking.mapper.BusMapper;
import by.alex.busbooking.repository.BusRepository;
import by.alex.busbooking.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BusServiceImpl implements BusService {
    private final BusRepository busRepository;
    private final BusMapper busMapper;

    @Override
    public BusDTO getBus(int id) {
        Bus bus = busRepository.getById(id);
        return busMapper.busToBusDTO(bus);
    }

    @Override
    public BusDTO addBus(BusDTO busDTO) {
        Bus bus = busMapper.busDTOtoBus(busDTO);
        Bus savedBus = busRepository.save(bus);
        return busMapper.busToBusDTO(savedBus);
    }

    @Override
    public List<BusDTO> getBuses() {
        List<Bus> buses = busRepository.findAll();
        return busMapper.busToBusDTO(buses);
    }

    @Override
    public void deleteBus(int id) {
        busRepository.deleteById(id);
    }
}
