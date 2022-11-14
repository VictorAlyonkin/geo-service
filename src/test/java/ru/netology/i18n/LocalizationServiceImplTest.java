package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.AbstractTest;
import ru.netology.entity.Country;

public class LocalizationServiceImplTest extends AbstractTest {
    private LocalizationService localizationService;

    @Override
    @BeforeEach
    public void init() {
        localizationService = new LocalizationServiceImpl();
    }

    @Test
    public void localeRussianTest() {
        String answer = localizationService.locale(Country.RUSSIA);
        Assertions.assertEquals("Добро пожаловать", answer);
    }

    @Test
    public void localeBrazilTest() {
        String answer = localizationService.locale(Country.BRAZIL);
        Assertions.assertEquals("Welcome", answer);
    }
}
