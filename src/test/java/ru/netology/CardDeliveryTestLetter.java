package ru.netology;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTestLetter {

    LocalDate today = LocalDate.now();
    LocalDate meetingDate = LocalDate.now().plusDays(7);
    String day = meetingDate.format(DateTimeFormatter.ofPattern("d"));

    @Test
    void shouldCardDeliveryOrderWeekLater() {
        open("http://localhost:9999");
        $("[type='text']").setValue("Ка");
        $(withText("Калининград")).click();
        $("[type=\"button\"].icon-button").click();
        for (int i = 0; i < (meetingDate.getMonthValue() - today.getMonthValue()); i++) {
            $("[data-step='1']").click();
        }
        $$("td.calendar__day").find(exactText(day)).click();
        $("[data-test-id= name] input.input__control ").setValue("Юрий Петров");
        $("[data-test-id= phone] input.input__control ").setValue("+79270000000");
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
        $("[data-test-id=notification] .notification__content").waitUntil(visible, 15000).shouldHave(text(meetingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
    }


}

