package org.sergei.axon.booking.facade;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.sergei.axon.booking.async.AsyncOperationService;
import org.sergei.axon.booking.command.BookingCommand;
import org.sergei.axon.booking.query.BookingQuery;
import org.sergei.axon.booking.storage.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingFacade {

    private static final Logger LOG = LoggerFactory.getLogger(BookingFacade.class);

    private final AsyncOperationService asyncService;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    public BookingFacade(AsyncOperationService asyncService,
                         QueryGateway queryGateway,
                         CommandGateway commandGateway) {
        this.asyncService = asyncService;
        this.queryGateway = queryGateway;
        this.commandGateway = commandGateway;
    }

    @GetMapping("/async/{param}")
    public ResponseEntity<String> executeAsync(@PathVariable("param") String param) {
        try {
            CompletableFuture<String> uppercaseFuture = asyncService.uppercaseString(param);
            CompletableFuture<String> appendedFuture = asyncService.appendString(param);
            CompletableFuture<String> substringFuture = asyncService.substringString(param);

            CompletableFuture.allOf(uppercaseFuture, appendedFuture, substringFuture).join();

            String response = uppercaseFuture.get() + appendedFuture.get() + substringFuture.get();

            return ResponseEntity.ok().body(response);
        } catch (InterruptedException | ExecutionException e) {
            LOG.error(ExceptionUtils.getStackTrace(e));
            return ResponseEntity.badRequest().build();
        }
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
