package org.sergei.axon.booking.event;

import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BookingReceivedEvent {

    private final UUID uuid;
    private final String destination;

    public BookingReceivedEvent(UUID uuid, String destination) {
        this.uuid = uuid;
        this.destination = destination;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
