package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.AbstractTest;
import ru.netology.entity.Country;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;

public class MessageSenderImplTest extends AbstractTest {

    @Override
    @BeforeEach
    protected void init() {
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
}
