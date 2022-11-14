package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.AbstractTest;
import ru.netology.entity.Location;

public class GeoServiceImplTest extends AbstractTest {

    @Override
    @BeforeEach
    public void init() {
        geoService = new GeoServiceImpl();
    }

    @Test
    public void byIpMoscow() {
        Location locationMoscow = geoService.byIp(MOSCOW_IP);
        checkAssertEquals(MOSCOW_LOCATION, locationMoscow);
    }

    @Test
    public void byIpNewYork() {
        Location locationNewYork = geoService.byIp(NEW_YORK_IP);
        checkAssertEquals(NEW_YORK_LOCATION, locationNewYork);
    }

    private void checkAssertEquals(Location expected, Location actual) {
        Assertions.assertEquals(expected.getCity(), actual.getCity());
        Assertions.assertEquals(expected.getCountry(), actual.getCountry());
        Assertions.assertEquals(expected.getStreet(), actual.getStreet());
        Assertions.assertEquals(expected.getBuiling(), actual.getBuiling());
    }
}
