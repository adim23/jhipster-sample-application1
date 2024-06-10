package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.BookingTestSamples.*;
import static com.mycompany.myapp.domain.PassengerTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class PassengerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Passenger.class);
        Passenger passenger1 = getPassengerSample1();
        Passenger passenger2 = new Passenger();
        assertThat(passenger1).isNotEqualTo(passenger2);

        passenger2.setId(passenger1.getId());
        assertThat(passenger1).isEqualTo(passenger2);

        passenger2 = getPassengerSample2();
        assertThat(passenger1).isNotEqualTo(passenger2);
    }

    @Test
    void bookingsTest() {
        Passenger passenger = getPassengerRandomSampleGenerator();
        Booking bookingBack = getBookingRandomSampleGenerator();

        passenger.addBookings(bookingBack);
        assertThat(passenger.getBookings()).containsOnly(bookingBack);
        assertThat(bookingBack.getPassenger()).isEqualTo(passenger);

        passenger.removeBookings(bookingBack);
        assertThat(passenger.getBookings()).doesNotContain(bookingBack);
        assertThat(bookingBack.getPassenger()).isNull();

        passenger.bookings(new HashSet<>(Set.of(bookingBack)));
        assertThat(passenger.getBookings()).containsOnly(bookingBack);
        assertThat(bookingBack.getPassenger()).isEqualTo(passenger);

        passenger.setBookings(new HashSet<>());
        assertThat(passenger.getBookings()).doesNotContain(bookingBack);
        assertThat(bookingBack.getPassenger()).isNull();
    }
}
