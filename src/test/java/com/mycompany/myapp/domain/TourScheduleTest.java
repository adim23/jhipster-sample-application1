package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.BookingTestSamples.*;
import static com.mycompany.myapp.domain.DriverTestSamples.*;
import static com.mycompany.myapp.domain.TourScheduleTestSamples.*;
import static com.mycompany.myapp.domain.TourTestSamples.*;
import static com.mycompany.myapp.domain.VehicleTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class TourScheduleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TourSchedule.class);
        TourSchedule tourSchedule1 = getTourScheduleSample1();
        TourSchedule tourSchedule2 = new TourSchedule();
        assertThat(tourSchedule1).isNotEqualTo(tourSchedule2);

        tourSchedule2.setId(tourSchedule1.getId());
        assertThat(tourSchedule1).isEqualTo(tourSchedule2);

        tourSchedule2 = getTourScheduleSample2();
        assertThat(tourSchedule1).isNotEqualTo(tourSchedule2);
    }

    @Test
    void bookingsTest() {
        TourSchedule tourSchedule = getTourScheduleRandomSampleGenerator();
        Booking bookingBack = getBookingRandomSampleGenerator();

        tourSchedule.addBookings(bookingBack);
        assertThat(tourSchedule.getBookings()).containsOnly(bookingBack);
        assertThat(bookingBack.getSchedule()).isEqualTo(tourSchedule);

        tourSchedule.removeBookings(bookingBack);
        assertThat(tourSchedule.getBookings()).doesNotContain(bookingBack);
        assertThat(bookingBack.getSchedule()).isNull();

        tourSchedule.bookings(new HashSet<>(Set.of(bookingBack)));
        assertThat(tourSchedule.getBookings()).containsOnly(bookingBack);
        assertThat(bookingBack.getSchedule()).isEqualTo(tourSchedule);

        tourSchedule.setBookings(new HashSet<>());
        assertThat(tourSchedule.getBookings()).doesNotContain(bookingBack);
        assertThat(bookingBack.getSchedule()).isNull();
    }

    @Test
    void tourTest() {
        TourSchedule tourSchedule = getTourScheduleRandomSampleGenerator();
        Tour tourBack = getTourRandomSampleGenerator();

        tourSchedule.setTour(tourBack);
        assertThat(tourSchedule.getTour()).isEqualTo(tourBack);

        tourSchedule.tour(null);
        assertThat(tourSchedule.getTour()).isNull();
    }

    @Test
    void vehicleTest() {
        TourSchedule tourSchedule = getTourScheduleRandomSampleGenerator();
        Vehicle vehicleBack = getVehicleRandomSampleGenerator();

        tourSchedule.setVehicle(vehicleBack);
        assertThat(tourSchedule.getVehicle()).isEqualTo(vehicleBack);

        tourSchedule.vehicle(null);
        assertThat(tourSchedule.getVehicle()).isNull();
    }

    @Test
    void driverTest() {
        TourSchedule tourSchedule = getTourScheduleRandomSampleGenerator();
        Driver driverBack = getDriverRandomSampleGenerator();

        tourSchedule.setDriver(driverBack);
        assertThat(tourSchedule.getDriver()).isEqualTo(driverBack);

        tourSchedule.driver(null);
        assertThat(tourSchedule.getDriver()).isNull();
    }
}
