package org.sergei.axon.booking.storage;

import java.util.HashMap;
import java.util.Map;

public final class BookingStorage {

    private BookingStorage() {
        // noop
    }

    private static final Map<String, Booking> BOOKING_MAP = new HashMap<>();

    public static Map<String, Booking> getBookingMap() {
        return BOOKING_MAP;
    }
}
