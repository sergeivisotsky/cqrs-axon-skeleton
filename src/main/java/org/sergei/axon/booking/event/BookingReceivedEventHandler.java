package org.sergei.axon.booking.event;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BookingReceivedEventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(BookingReceivedEventHandler.class);

    @EventSourcingHandler
    public void handle(BookingReceivedEvent event) {

        LOG.info("An event received: {}", event.toString());

    }

}
