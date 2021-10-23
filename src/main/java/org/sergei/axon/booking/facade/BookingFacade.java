package org.sergei.axon.booking.facade;

import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.sergei.axon.booking.command.BookingCommand;
import org.sergei.axon.booking.query.BookingQuery;
import org.sergei.axon.booking.storage.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingFacade {

    private static final Logger LOG = LoggerFactory.getLogger(BookingFacade.class);

    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    public BookingFacade(QueryGateway queryGateway, CommandGateway commandGateway) {
        this.queryGateway = queryGateway;
        this.commandGateway = commandGateway;
    }

    @PostMapping("/query")
    public ResponseEntity<Booking> queryBooking(@RequestBody BookingQuery query) {
        try {
            Booking booking = queryGateway.query(query, Booking.class).get();
            return ResponseEntity.ok(booking);
        } catch (InterruptedException | ExecutionException e) {
            LOG.error(ExceptionUtils.getStackTrace(e));
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<BookingResponse> saveBooking(@RequestBody BookingRequest request) {
        commandGateway.send(
                new BookingCommand(request.getDestination(), request.getPrice(), request.getDeparture())
        );
        return ResponseEntity
                .ok()
                .body(new BookingResponse(request.getDestination()));
    }

}
