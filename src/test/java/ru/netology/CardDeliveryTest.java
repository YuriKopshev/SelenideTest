package ru.netology;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {



    @Test
     void shouldCardDeliveryOrderSuccess(){
        open("http://localhost:9999");
        $("[type='text']").setValue("Москва").pressEnter();
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue("23.09.2020");
        $$("[type='text']").last().setValue("Юрий Петров");
        $$("[type='tel']").last().setValue("+79270000000");
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(visible,15000);
    }

    @Test
    void shouldCardDeliveryOrderFailByDate(){
        open("http://localhost:9999");
        $("[type='text']").setValue("Москва").pressEnter();
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue("19.09.2020");
        $$("[type='text']").last().setValue("Юрий Петров");
        $$("[type='tel']").last().setValue("+79270000000");
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Забронировать")).click();
        $(withText("Заказ на выбранную дату невозможен")).waitUntil(visible,3000);
    }

    @Test
    void shouldCardDeliveryOrderFailByCity(){
        open("http://localhost:9999");
        $("[type='text']").setValue("Чертаново").pressEnter();
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue("23.09.2020");
        $$("[type='text']").last().setValue("Юрий Петров");
        $$("[type='tel']").last().setValue("+79270000000");
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Забронировать")).click();
        $(withText("Доставка в выбранный город недоступна")).waitUntil(visible,3000);
    }

    @Test
    void shouldCardDeliveryOrderFailByName(){
        open("http://localhost:9999");
        $("[type='text']").setValue("Майкоп").pressEnter();
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue("23.09.2020");
        $$("[type='text']").last().setValue("Adam Smith");
        $$("[type='tel']").last().setValue("+79270000000");
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Забронировать")).click();
        $(withText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).waitUntil(visible,3000);
    }

    @Test
    void shouldCardDeliveryOrderFailByTel(){
        open("http://localhost:9999");
        $("[type='text']").setValue("Москва").pressEnter();
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue("23.09.2020");
        $$("[type='text']").last().setValue("Юрий Петров");
        $$("[type='tel']").last().setValue("+7927000000");
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Забронировать")).click();
        $(withText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).waitUntil(visible,3000);
    }

    @Test
    void shouldCardDeliveryOrderFailByAgreement(){
        open("http://localhost:9999");
        $("[type='text']").setValue("Москва").pressEnter();
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue("23.09.2020");
        $$("[type='text']").last().setValue("Юрий Петров");
        $$("[type='tel']").last().setValue("+79270000000");
        $$("[type='button']").find(exactText("Забронировать")).click();
        $("[data-test-id='agreement'].input_invalid ").shouldBe(visible);
    }




}
