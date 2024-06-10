package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.ImageFileTestSamples.*;
import static com.mycompany.myapp.domain.VehicleTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class VehicleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vehicle.class);
        Vehicle vehicle1 = getVehicleSample1();
        Vehicle vehicle2 = new Vehicle();
        assertThat(vehicle1).isNotEqualTo(vehicle2);

        vehicle2.setId(vehicle1.getId());
        assertThat(vehicle1).isEqualTo(vehicle2);

        vehicle2 = getVehicleSample2();
        assertThat(vehicle1).isNotEqualTo(vehicle2);
    }

    @Test
    void imagesTest() {
        Vehicle vehicle = getVehicleRandomSampleGenerator();
        ImageFile imageFileBack = getImageFileRandomSampleGenerator();

        vehicle.addImages(imageFileBack);
        assertThat(vehicle.getImages()).containsOnly(imageFileBack);
        assertThat(imageFileBack.getVehicle()).isEqualTo(vehicle);

        vehicle.removeImages(imageFileBack);
        assertThat(vehicle.getImages()).doesNotContain(imageFileBack);
        assertThat(imageFileBack.getVehicle()).isNull();

        vehicle.images(new HashSet<>(Set.of(imageFileBack)));
        assertThat(vehicle.getImages()).containsOnly(imageFileBack);
        assertThat(imageFileBack.getVehicle()).isEqualTo(vehicle);

        vehicle.setImages(new HashSet<>());
        assertThat(vehicle.getImages()).doesNotContain(imageFileBack);
        assertThat(imageFileBack.getVehicle()).isNull();
    }
}
