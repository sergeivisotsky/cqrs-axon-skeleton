package org.sergei.axon.booking.query;

import org.axonframework.queryhandling.QueryHandler;
import org.sergei.axon.booking.storage.BookingStorage;
import org.sergei.axon.booking.storage.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingQueryHandler {

    @QueryHandler
    public Booking queryBooking(BookingQuery query) {
        return BookingStorage.getBookingMap().get(query.getDestination());
    }

}
