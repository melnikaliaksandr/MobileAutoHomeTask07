package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
            SKIP_ONBOARDING_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button",
            SEARCH_INIT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_container']//*[@class='android.widget.TextView']",
            SEARCH_INPUT = "id:org.wikipedia:id/search_src_text",
            LIST_OF_ELEMENTS = "id:org.wikipedia:id/page_list_item_title",
            SEARCH_CLOSE_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_TITLE_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']",
            SEARCH_RESULT_DESCRIPTION_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{SUBSTRING}']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openArticleWithDescription(String description) {
        String descriptionXpath = replaceTemplate(SEARCH_RESULT_DESCRIPTION_BY_SUBSTRING_TPL, description);
        this.waitForElementAndClick(
                descriptionXpath,
                "Cannot find search result with title: " + descriptionXpath,
                15);
    }

    public void openArticleWithTitle(String title) {
        String titleXpath = replaceTemplate(SEARCH_RESULT_TITLE_BY_SUBSTRING_TPL, title);
        this.waitForElementAndClick(
                titleXpath,
                "Cannot find search result with title: " + title,
                15);
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = replaceTemplate(SEARCH_RESULT_TITLE_BY_SUBSTRING_TPL, substring);
        this.waitForElementPresent(searchResultXpath,
                "Cannot find search result with substring: " + substring);
    }

    private static String replaceTemplate(String template, String substring) {
        return template.replace("{SUBSTRING}", substring);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find search button",
                10);
        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find search button");
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(
                SEARCH_INPUT, searchLine,
                "Cannot send text in search field",
                10);
    }

    public List<WebElement> getListOfElements() {
        return this.waitAndReturnListOfElements(
                LIST_OF_ELEMENTS,
                "Cannot find the articles",
                15);
    }

    public boolean listOfElementsIsEmpty() {
        return this.waitForElementNotPresent(
                LIST_OF_ELEMENTS,
                "Articles is still present on the page",
                5);
    }

    public void resetSearch() {
        this.waitForElementAndClick(
                SEARCH_CLOSE_BUTTON,
                "Cannot find X button",
                10);
    }

    public void skipOnboarding() {
        this.waitForElementAndClick(
                SKIP_ONBOARDING_BUTTON,
                "Cannot find 'Skip' button",
                10);
    }
}
