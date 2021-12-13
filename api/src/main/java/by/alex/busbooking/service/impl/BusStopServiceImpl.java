package by.alex.busbooking.service.impl;

import by.alex.busbooking.repository.BusStopRepository;
import by.alex.busbooking.service.BusStopService;
import by.alex.busbooking.dto.BusStopDTO;
import by.alex.busbooking.entity.BusStop;
import by.alex.busbooking.mapper.BusStopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BusStopServiceImpl implements BusStopService {
    private final BusStopRepository busStopRepository;
    private final BusStopMapper busStopMapper;

    @Override
    public BusStopDTO getBusStop(int id) {
        BusStop busStop = busStopRepository.getById(id);
        return busStopMapper.busStopToBusStopDTO(busStop);
    }

    @Override
    public BusStopDTO addBusStop(BusStopDTO busStopDTO) {
        BusStop busStop = busStopMapper.busStopDTOtoBusStop(busStopDTO);
        BusStop savedBusStop = busStopRepository.save(busStop);
        return busStopMapper.busStopToBusStopDTO(savedBusStop);
    }

    @Override
    public List<BusStopDTO> getBusStops() {
        List<BusStop> busStops = busStopRepository.findAll();
        return busStopMapper.busStopToBusStopDTO(busStops);
    }

    @Override
    public void deleteBusStop(int id) {
        busStopRepository.deleteById(id);
    }
}
