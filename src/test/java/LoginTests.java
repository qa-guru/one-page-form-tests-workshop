import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class LoginTests extends TestBase {

    @Test
    public void successfulAuthorization() {
        open("/login.html");
        $("[data-testid=login-form-title]").shouldHave(text("Login Form"));

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=login-form]").shouldNotBe(visible);
        $("[data-testid=logged-in-title]").shouldHave(text("Logged In Form"));
        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    @Test
//    @Disabled("Login by Press Enter is not implemented yet")
    public void successfulAuthorizationWithPressEnter() {
        open("/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1").pressEnter();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    @Test
    public void wrongPasswordAuthorizationTest() {
        open("/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("wrong password");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }
}
