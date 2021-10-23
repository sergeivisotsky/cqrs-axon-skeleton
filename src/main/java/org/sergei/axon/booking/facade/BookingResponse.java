package org.sergei.axon.booking.facade;

public class BookingResponse {

    private final String destination;

    public BookingResponse(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }
}
