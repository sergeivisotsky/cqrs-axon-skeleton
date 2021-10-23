package org.sergei.axon.booking.command;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BookingCommand {

    @TargetAggregateIdentifier
    private final String destination;
    private final BigDecimal price;
    private final String departure;

    public BookingCommand(String destination, BigDecimal price, String departure) {
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
