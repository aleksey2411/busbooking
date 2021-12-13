package by.alex.busbooking.service.impl;

import by.alex.busbooking.dto.TicketDTO;
import by.alex.busbooking.entity.*;
import by.alex.busbooking.exception.DifferentRoutesException;
import by.alex.busbooking.exception.NotEnoughPlacesException;
import by.alex.busbooking.mapper.TicketMapper;
import by.alex.busbooking.repository.RouteBusStopRepository;
import by.alex.busbooking.repository.TicketRepository;
import by.alex.busbooking.repository.TripRepository;
import by.alex.busbooking.repository.UserRepository;
import by.alex.busbooking.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TripRepository tripRepository;
    private final RouteBusStopRepository routeBusStopRepository;
    private final UserRepository userRepository;
    private final TicketMapper ticketMapper;

    @Transactional(readOnly = true)
    @Override
    public TicketDTO getTicket(int id, Authentication auth) {
        Ticket ticket = ticketRepository.getById(id);
        User user = userRepository.findByEmail(auth.getName());

        if (ticket.getUser().getId() != user.getId() &&
                auth.getAuthorities().stream().noneMatch(role -> role.getAuthority().equals("ADMIN"))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
        }

        return ticketMapper.ticketToTicketDTO(ticket);
    }

    @Override
    public TicketDTO confirmTicket(int id) {
        Ticket ticket = ticketRepository.getById(id);
        ticket.setTicketStatus(TicketStatus.CONFIRMED);

        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.ticketToTicketDTO(savedTicket);
    }

    @Override
    public TicketDTO rejectTicket(int id) {
        Ticket ticket = ticketRepository.getById(id);
        ticket.setTicketStatus(TicketStatus.REJECTED);

        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.ticketToTicketDTO(savedTicket);
    }

    @Override
    public TicketDTO buyTicket(TicketDTO ticketDTO, String currentUsername) {
        Ticket ticket = ticketMapper.ticketDTOtoTicketDTO(ticketDTO);
        ticket.setOrderDateTime(LocalDateTime.now());

        Trip trip = tripRepository.getById(ticket.getTrip().getId());
        RouteBusStop routeBusStop = routeBusStopRepository.getById(ticket.getRouteBusStop().getId());

        if (routeBusStop.getRoute() != trip.getRoute()) {
            throw new DifferentRoutesException();
        }

        final int numberOfFreSeats = ticketRepository.getNumberOfFreSeats(ticket.getTrip().getId());

        if (numberOfFreSeats - ticket.getNumberOfSeats() < 0) {
            throw new NotEnoughPlacesException();
        }

        final int timeToBusStopInMinutes = routeBusStop.getTimeToBusStopInMinutes();
        final LocalDateTime tripDepartureDateTime = trip.getDepartureDateTime();
        final LocalDateTime departureDateTime = tripDepartureDateTime.plusMinutes(timeToBusStopInMinutes);

        User user = userRepository.findByEmail(currentUsername);

        ticket.setDepartureDateTime(departureDateTime);
        ticket.setUser(user);
        ticket.setTicketStatus(TicketStatus.BOOKED);

        Ticket savedTicket = ticketRepository.save(ticket);

        return ticketMapper.ticketToTicketDTO(savedTicket);
    }

    @Override
    public List<TicketDTO> getTickets(Authentication auth) {
        LocalDate date = LocalDate.now();
        final LocalDateTime startDate = date.atStartOfDay();
        final LocalDateTime endDate = date.atTime(LocalTime.MAX);
        List<Ticket> tickets = ticketRepository.getAllByDepartureDateTimeBetween(startDate, endDate);

        return ticketMapper.ticketToTicketDTO(tickets);
    }

    @Override
    public List<TicketDTO> getTicketsByUserId(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName());
        List<Ticket> tickets = ticketRepository.getAllByUserId(user.getId());
        System.out.println(tickets);

        return ticketMapper.ticketToTicketDTO(tickets);
    }

    @Override
    public void deleteTicket(int id, String currentUsername) {
        Ticket ticket = ticketRepository.getById(id);

        if (ticket.getUser().getEmail() != currentUsername) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
        }

        ticketRepository.delete(ticket);
    }
}
