package by.alex.busbooking.service.impl;

import by.alex.busbooking.dto.RouteBusStopDTO;
import by.alex.busbooking.entity.BusStop;
import by.alex.busbooking.entity.Route;
import by.alex.busbooking.entity.RouteBusStop;
import by.alex.busbooking.exception.DifferentCitiesException;
import by.alex.busbooking.mapper.RouteBusStopMapper;
import by.alex.busbooking.repository.BusStopRepository;
import by.alex.busbooking.repository.RouteBusStopRepository;
import by.alex.busbooking.repository.RouteRepository;
import by.alex.busbooking.service.RouteBusStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class RouteBusStopServiceImpl implements RouteBusStopService {
    private final RouteBusStopRepository routeBusStopRepository;
    private final BusStopRepository busStopRepository;
    private final RouteRepository routeRepository;
    private final RouteBusStopMapper routeBusStopMapper;

    @Override
    public RouteBusStopDTO getRouteBusStop(int id) {
        RouteBusStop routeBusStop = routeBusStopRepository.getById(id);
        return routeBusStopMapper.routeBusStopToRouteBusStopDTO(routeBusStop);
    }

    @Override
    public RouteBusStopDTO addRouteBusStop(RouteBusStopDTO routeBusStopDTO) {
        RouteBusStop routeBusStop = routeBusStopMapper.routeBusStopDTOtoRouteBusStop(routeBusStopDTO);
        BusStop busStop = busStopRepository.getById(routeBusStop.getBusStop().getId());
        Route route = routeRepository.getById(routeBusStop.getRoute().getId());

        if (busStop.getCity() != route.getArrivalCity()) {
            routeBusStopRepository.delete(routeBusStop);
            throw new DifferentCitiesException();
        }

        RouteBusStop savedRouteBusStop = routeBusStopRepository.save(routeBusStop);
        return routeBusStopMapper.routeBusStopToRouteBusStopDTO(savedRouteBusStop);
    }

    @Override
    public List<RouteBusStopDTO> getRouteBusStopsByRouteId(int routeId) {
        List<RouteBusStop> routeBusStops = routeBusStopRepository.getAllByRouteId(routeId);

        return routeBusStopMapper.routeBusStopToRouteBusStopDTO(routeBusStops);
    }

    @Override
    public List<RouteBusStopDTO> getRouteBusStops() {
        List<RouteBusStop> routeBusStops = routeBusStopRepository.findAll();
        return routeBusStopMapper.routeBusStopToRouteBusStopDTO(routeBusStops);
    }

    @Override
    public void deleteRouteBusStop(int id) {
        routeBusStopRepository.deleteById(id);
    }
}
