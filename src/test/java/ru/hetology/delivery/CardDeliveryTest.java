package ru.hetology.delivery;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {

    @Test
    public void shouldSendForm() {
        Calendar calendar = new GregorianCalendar();
        calendar.roll(Calendar.DATE, +4);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String date = dateFormat.format(calendar.getTime());

        open ("http://localhost:9999/");
        $ ("[placeholder='Город']").setValue("Санкт-Петербург");
        for (int i=0; i<8; i++) {
            $("[placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        }
        $ ("[placeholder='Дата встречи']").setValue(date);
        $ ("[name='name']").setValue("Сюарт Мария");
        $ ("[name='phone']").setValue("+71110623421");
        $ (".checkbox__box").click();
        $ (".button__text").click();
        $ ("[data-test-id='notification']").shouldBe(Condition.appear, Duration.ofSeconds(15));
    }
}
