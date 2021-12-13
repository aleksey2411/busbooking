package by.alex.busbooking.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TicketStatus {
    BOOKED, CONFIRMED, REJECTED;

    @JsonValue
    public String getStatus() {
        return this.name();
    }
}
