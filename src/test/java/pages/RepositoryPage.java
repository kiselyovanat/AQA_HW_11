package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class RepositoryPage {
    private final String url = "/";
    private final SelenideElement
            searchInput = $(".header-search-input"),
            issuesTab = $("#issues-tab");

    @Step("Открываем главную страницу Github")
    public RepositoryPage openPage() {
        open(url);

        return this;
    }

    @Step("Выполняем поиск репозитория {repositoryName}")
    public RepositoryPage searchRepository(String repositoryName) {
        searchInput.click();
        searchInput.sendKeys(repositoryName);
        searchInput.pressEnter();

        return this;
    }

    @Step("Переходим в репозиторий {repositoryName}")
    public RepositoryPage openRepository(String repositoryName) {
        $(linkText(repositoryName)).click();

        return this;
    }

    @Step("Открываем Issues")
    public RepositoryPage openIssues() {
        issuesTab.click();

        return this;
    }

    @Step("Проверяем наличие записи в Issues с номером {issue}")
    public RepositoryPage checkIssueExists(String issue) {
        $(withText(issue)).should(Condition.exist);

        return this;
    }
}
