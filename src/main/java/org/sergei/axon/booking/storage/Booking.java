package org.sergei.axon.booking.storage;

import java.math.BigDecimal;

public class Booking {

    private final String destination;
    private final BigDecimal price;
    private final String departure;

    public Booking(String destination, BigDecimal price, String departure) {
        this.destination = destination;
        this.price = price;
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDeparture() {
        return departure;
    }
}
