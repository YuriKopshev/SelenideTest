package ru.netology;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTestLetter {
    @Test
    void shouldCardDeliveryOrderWeekLater() {
        open("http://localhost:9999");
        $("[type='text']").setValue("Ка");
        $(withText("Калининград")).click();
        $("[type=\"button\"].icon-button").click();
        $$("td.calendar__day").find(exactText("27")).click();
        $$("[type='text']").last().setValue("Юрий Петров");
        $$("[type='tel']").last().setValue("+79270000000");
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }

    @Test
    void shouldCardDeliveryOrderMonthLater() {
        open("http://localhost:9999");
        $("[type='text']").setValue("Ка");
        $(withText("Калининград")).click();
        $("[type=\"button\"].icon-button").click();
        $("[class=\"calendar__arrow calendar__arrow_direction_right\"]").click();
        $$("td.calendar__day").find(exactText("20")).click();
        $$("[type='text']").last().setValue("Юрий Петров");
        $$("[type='tel']").last().setValue("+79270000000");
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }
}

