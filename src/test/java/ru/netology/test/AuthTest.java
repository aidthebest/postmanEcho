package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.Registration.getRegisteredUser;

public class AuthTest {
    @BeforeEach
    void setup() {
        Configuration.headless = true;
        open("http://localhost:9999");
    }

    @BeforeAll
    static void setUpAll() {
    }

    @Test

    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        var user = getRegisteredUser("active");
//        DataGenerator.RegistrationDto user=DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id='login'] input").sendKeys(user.getLogin());
        $("[data-test-id='password'] input").sendKeys(user.getPassword());
        $(byText("Продолжить")).click();
        $(byText("Личный кабинет")).shouldBe(visible);
    }

    //        @Test
//        @DisplayName("Should successfully login with active registered user")
//        void shouldSuccessfulLoginIfRegisteredActiveUser() {
//            var registeredUser = getRegisteredUser("active");
//            $("[data-test-id=login] input").setValue(registeredUser.getLogin());
//            $("[data-test-id=password] input").setValue(registeredUser.getPassword());
//            $("[data-test-id='action-login']").click();
//            $$(".heading").find(exactText("Личный кабинет")).should(visible);
//
//        }
//
//        @Test
//        @DisplayName("Should get error message if login with not registered user")
//        void shouldGetErrorIfNotRegisteredUser() {
//            var unRegisteredUser = getUser("active");
//            $("[data-test-id=login] input").setValue(unRegisteredUser.getLogin());
//            $("[data-test-id=password] input").setValue(unRegisteredUser.getPassword());
//            $("[data-test-id='action-login']").click();
//            $(withText("Неверно указан логин или пароль")).should(visible);
//
//        }

        @Test
        @DisplayName("Should get error message if login with blocked registered user")
        void shouldGetErrorIfBlockedUser() {
            var blockedUser = getRegisteredUser("blocked");
            $("[data-test-id=login] input").setValue(blockedUser.getLogin());
            $("[data-test-id=password] input").setValue(blockedUser.getPassword());
            $("[data-test-id='action-login']").click();
            $(withText("Пользователь заблокирован")).should(visible);

        }

//        @Test
//        @DisplayName("Should get error message if login with wrong login")
//        void shouldGetErrorIfWrongLogin() {
//            var registeredUser = getRegisteredUser("active");
//            var invalidLogin = getRandomLogin();
//            $("[data-test-id=login] input").setValue(invalidLogin);
//            $("[data-test-id=password] input").setValue(registeredUser.getPassword());
//            $("[data-test-id='action-login']").click();
//            $(withText("Неверно указан логин или пароль")).should(visible);
//
//        }
//
//        @Test
//        @DisplayName("Should get error message if login with wrong password")
//        void shouldGetErrorIfWrongPassword() {
//            var registeredUser = getRegisteredUser("active");
//            var invalidPassword = getRandomPassword();
//            $("[data-test-id=login] input").setValue(registeredUser.getLogin());
//            $("[data-test-id=password] input").setValue(invalidPassword);
//            $("[data-test-id='action-login']").click();
//            $(withText("Неверно указан логин или пароль")).should(visible);
//
//        }
//    }


}
