package org.sergei.axon.booking.command;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.sergei.axon.booking.event.BookingReceivedEvent;
import org.sergei.axon.booking.storage.Booking;
import org.sergei.axon.booking.storage.BookingStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aggregate
public class BookingCommandHandler {

    private static final Logger LOG = LoggerFactory.getLogger(BookingCommandHandler.class);

    @AggregateIdentifier
    private String destination;

    @CommandHandler
    public BookingCommandHandler(BookingCommand command) {
        LOG.info("~~~ The following booking received: {} ~~~", command.toString());

        destination = command.getDestination();

        BookingStorage.getBookingMap().put(
                command.getDestination(),
                new Booking(destination, command.getPrice(), command.getDeparture())
        );

        AggregateLifecycle.apply(new BookingReceivedEvent(UUID.randomUUID(), destination));
    }

    public BookingCommandHandler() {
    }
}
