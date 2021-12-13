package by.alex.busbooking.service;

import by.alex.busbooking.dto.TicketDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TicketService {
    TicketDTO getTicket(int id, Authentication auth);

    TicketDTO confirmTicket(int id);

    TicketDTO rejectTicket(int id);

    TicketDTO buyTicket(TicketDTO ticketDTO, String currentUsername);

    List<TicketDTO> getTickets(Authentication auth);

    void deleteTicket(int id, String currentUsername);

    List<TicketDTO> getTicketsByUserId(Authentication authentication);
}
