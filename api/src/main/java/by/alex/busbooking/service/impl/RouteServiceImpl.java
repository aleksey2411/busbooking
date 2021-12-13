package by.alex.busbooking.service.impl;

import by.alex.busbooking.dto.RouteDTO;
import by.alex.busbooking.entity.Route;
import by.alex.busbooking.mapper.RouteMapper;
import by.alex.busbooking.repository.RouteRepository;
import by.alex.busbooking.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;

    @Override
    public RouteDTO getRoute(int id) {
        Route route = routeRepository.getById(id);
        return routeMapper.routeToRouteDTO(route);
    }

    @Override
    public RouteDTO getRouteByCities(int arrivalCityId, int departureCityId) {
        List<Route> routes = routeRepository.findAll();
        Route routeOpt = routes.stream()
                .filter(route -> route.getArrivalCity().getId() == arrivalCityId)
                .filter(route -> route.getDepartureCity().getId() == departureCityId)
                .findFirst().get();

        return routeMapper.routeToRouteDTO(routeOpt);
    }

    @Override
    public RouteDTO addRoute(RouteDTO routeDTO) {
        Route route = routeMapper.routeDTOtoRoute(routeDTO);
        Route savedRoute = routeRepository.save(route);
        return routeMapper.routeToRouteDTO(savedRoute);
    }

    @Override
    public List<RouteDTO> getRoutes() {
        List<Route> routes = routeRepository.findAll();
        return routeMapper.routeToRouteDTO(routes);
    }

    @Override
    public void deleteRoute(int id) {
        routeRepository.deleteById(id);
    }
}
