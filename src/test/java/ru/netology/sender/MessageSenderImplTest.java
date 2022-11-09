package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    private static final String RUSSIAN_ANSWER = "Добро пожаловать";
    private static final String ANOTHER_ANSWER = "Welcome";
    private static final Location MOSCOW_LOCATION = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
    private static final Location NEW_YORK_LOCATION = new Location("New York", Country.USA, " 10th Avenue", 32);
    private static final String MOSCOW_IP = GeoServiceImpl.MOSCOW_IP;
    private static final String NEW_YORK_IP = GeoServiceImpl.NEW_YORK_IP;

    private GeoService geoService;
    private LocalizationService localizationService;
    private MessageSender messageSender;
    private Map<String, String> headers;

    @BeforeEach
    public void init() {
        localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn(RUSSIAN_ANSWER);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn(ANOTHER_ANSWER);

        geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(MOSCOW_IP))
                .thenReturn(MOSCOW_LOCATION);
        Mockito.when(geoService.byIp(NEW_YORK_IP))
                .thenReturn(NEW_YORK_LOCATION);

        messageSender = new MessageSenderImpl(geoService, localizationService);
        headers = new HashMap<String, String>();
    }

    @Test
    public void sendRussianAnswer() {
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, MOSCOW_IP);
        String answer = messageSender.send(headers);
        Assertions.assertEquals(RUSSIAN_ANSWER, answer);
    }

    @Test
    public void sendAnotherAnswer() {
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, NEW_YORK_IP);
        String answer = messageSender.send(headers);
        Assertions.assertEquals(ANOTHER_ANSWER, answer);
    }

    @Test
    public void byIpMoscow() {
        Location locationMoscow = geoService.byIp(MOSCOW_IP);
        Assertions.assertEquals(MOSCOW_LOCATION, locationMoscow);
    }

    @Test
    public void byIpNewYork() {
        Location locationNewYork = geoService.byIp(NEW_YORK_IP);
        Assertions.assertEquals(NEW_YORK_LOCATION, locationNewYork);
    }
}
