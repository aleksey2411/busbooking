package by.alex.busbooking.mapper;

import by.alex.busbooking.dto.TicketDTO;
import by.alex.busbooking.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {UserMapper.class, TripMapper.class, RouteBusStopMapper.class})
public abstract class TicketMapper {
    @Mapping(target = "userDTO", source = "user")
    @Mapping(target = "tripDTO", source = "trip")
    @Mapping(target = "routeBusStopDTO", source = "routeBusStop")
    public abstract TicketDTO ticketToTicketDTO(Ticket ticket);

    @Mapping(target = "user", source = "userDTO")
    @Mapping(target = "trip", source = "tripDTO")
    @Mapping(target = "routeBusStop", source = "routeBusStopDTO")
    public abstract Ticket ticketDTOtoTicketDTO(TicketDTO ticketDTO);

    public abstract List<TicketDTO> ticketToTicketDTO(List<Ticket> tickets);
}
