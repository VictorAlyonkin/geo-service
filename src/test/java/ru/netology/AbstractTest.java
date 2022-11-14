package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;

import java.util.Map;

public abstract class AbstractTest {

    protected static final String RUSSIAN_ANSWER = "Добро пожаловать";
    protected static final String ANOTHER_ANSWER = "Welcome";
    protected static final Location MOSCOW_LOCATION = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
    protected static final Location NEW_YORK_LOCATION = new Location("New York", Country.USA, " 10th Avenue", 32);
    protected static final String MOSCOW_IP = GeoServiceImpl.MOSCOW_IP;
    protected static final String NEW_YORK_IP = GeoServiceImpl.NEW_YORK_IP;

    protected GeoService geoService;
    protected LocalizationService localizationService;
    protected MessageSender messageSender;
    protected Map<String, String> headers;

    @BeforeEach
    protected abstract void init();
}
