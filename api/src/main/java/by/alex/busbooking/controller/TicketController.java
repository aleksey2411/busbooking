package by.alex.busbooking.controller;

import by.alex.busbooking.dto.TicketDTO;
import by.alex.busbooking.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Tickets")
public class TicketController {
    private final TicketService ticketService;

    @Operation(summary = "Get all tickets")
    @ApiResponse(responseCode = "200", description = "Found the tickets",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = TicketDTO.class))})
    @GetMapping("/")
    public List<TicketDTO> getTickets() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ticketService.getTickets(auth);
    }

    @GetMapping("/users")
    public List<TicketDTO> getTicketsByUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ticketService.getTicketsByUserId(auth);
    }

    @Operation(summary = "Get a ticket by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the ticket",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TicketDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content)})
    @GetMapping("/{id}")
    public TicketDTO getTicket(@PathVariable int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ticketService.getTicket(id, auth);
    }

    @GetMapping("/{id}/confirm")
    public TicketDTO confirmTicket(@PathVariable int id) {
        return ticketService.confirmTicket(id);
    }

    @GetMapping("/{id}/reject")
    public TicketDTO rejectTicket(@PathVariable int id) {
        return ticketService.rejectTicket(id);
    }

    @Operation(summary = "Add new ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added new ticket",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TicketDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid validation",
                    content = @Content)})
    @PostMapping("/")
    public TicketDTO addTicket(@Valid @RequestBody TicketDTO ticketDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ticketService.buyTicket(ticketDTO, username);
    }

    @Operation(summary = "Delete ticket")
    @ApiResponse(responseCode = "200", description = "Deleted the ticket")
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable int id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ticketService.deleteTicket(id, username);
    }
}
