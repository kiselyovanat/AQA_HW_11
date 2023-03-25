import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import pages.RepositoryPage;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class CheckIssuesTest extends TestBase {
    public String
            repositoryName = "kiselyovanat/AI",
            issue = "#1";

    @Test
    public void checkIssueTestWithListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("/");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repositoryName);
        $(".header-search-input").pressEnter();
        $(linkText(repositoryName)).click();
        $("#issues-tab").click();
        $(withText(issue)).should(Condition.exist);
    }

    @Test
    public void checkIssueTestWithLambdaSteps() {

        step("Открываем главную страницу Github", () -> {
                    open("/");
                }
        );
        step("Выполняем поиск репозитория " + repositoryName, () -> {
                    $(".header-search-input").click();
                    $(".header-search-input").sendKeys(repositoryName);
                    $(".header-search-input").pressEnter();
                }
        );
        step("Переходим в репозиторий " + repositoryName, () -> {
                    $(linkText(repositoryName)).click();
                }
        );

        step("Открываем Issues", () -> {
                    $("#issues-tab").click();
                }
        );

        step("Проверяем наличие записи в Issues с номером " + issue, () -> {
                    $(withText(issue)).should(Condition.exist);
                }
        );
    }

    @Test
    public void checkIssueTestWitAnnotatedSteps() {
        RepositoryPage repositoryPage = new RepositoryPage();

        repositoryPage.openPage()
                .searchRepository(repositoryName)
                .openRepository(repositoryName)
                .openIssues()
                .checkIssueExists(issue);
    }

}

