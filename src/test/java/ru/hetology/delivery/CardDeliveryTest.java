package ru.hetology.delivery;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {

    @Test
    public void shouldSendForm() {
        Configuration.headless = true;
        String planningDate = generateDate(5);

        open ("http://localhost:9999/");
        $ ("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $ ("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $ ("[data-test-id='date'] input").setValue(planningDate);
        $ ("[data-test-id='name'] input").setValue("Сюарт Мария");
        $ ("[data-test-id='phone'] input").setValue("+71110623421");
        $ (".checkbox__box").click();
        $ (".button__text").click();
        $ ("[data-test-id='notification']").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $ (".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate));
    }

    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
