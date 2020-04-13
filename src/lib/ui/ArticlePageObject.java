package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            OVERFLOW_READING_LIST = "id:org.wikipedia:id/page_action_overflow_reading_lists",
            OVERFLOW_MENU_BUTTON = "id:org.wikipedia:id/page_toolbar_button_show_overflow_menu",
            NO_THANKS_BUTTON = "xpath://android.widget.Button[@text='NO THANKS']",
            DIALOG_CHECKBOX = "id:org.wikipedia:id/dialog_checkbox",
            NAVIGATE_UP_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
            BOOKMARK_MENU_BUTTON = "id:org.wikipedia:id/article_menu_bookmark",
            ONBOARDING_BUTTON = "id:org.wikipedia:id/onboarding_button",
            PATH_TO_READING_LIST_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{SUBSTRING}']",
            TITLE_OF_ARTICLE = "xpath://*[@resource-id='content']//android.view.View[@index='0']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String replaceTemplate(String template, String substring) {
        return template.replace("{SUBSTRING}", substring);
    }

    public WebElement getTitleElement() {
        return this.getElementByXpath(TITLE_OF_ARTICLE);
    }

    public String getTitleOfArticle() {
        return this.waitForElementAndGetAttribute(
                TITLE_OF_ARTICLE,
                "text",
                "Cannot get attribute text",
                20);
    }

    public void doNotSyncReadingList() {
        this.waitForElementAndClick(
                DIALOG_CHECKBOX,
                "Cannot find checkbox",
                10);
        this.waitForElementAndClick(
                NO_THANKS_BUTTON,
                "Cannot find 'No thanks' button",
                10);
    }

    public void openMyReadingList() {
        this.waitForElementAndClick(
                OVERFLOW_MENU_BUTTON,
                "Cannot find 'Menu' button",
                10);
        this.waitForElementAndClick(
                OVERFLOW_READING_LIST,
                "Cannot find 'My lists' button",
                10);
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                NAVIGATE_UP_BUTTON,
                "Cannot find 'Navigate up' button",
                10);
    }

    public void saveArticleToReadingList(String readingListName) {
        this.waitForElementAndClick(
                BOOKMARK_MENU_BUTTON,
                "Cannot find 'Save to reading list' button",
                10);
        this.waitForElementAndClick(
                ONBOARDING_BUTTON,
                "Cannot find 'Onboarding' button",
                10);
        String readingList = replaceTemplate(PATH_TO_READING_LIST_TPL, readingListName);
        this.waitForElementAndClick(
                readingList,
                "Cannot find list for saved articles",
                10);
    }

    public void saveArticleToReadingListAndCloseHint(String readingListName) {
        this.waitForElementAndClick(
                BOOKMARK_MENU_BUTTON,
                "Cannot find 'Save to reading list' button",
                10);
        this.waitForElementAndClick(
                BOOKMARK_MENU_BUTTON,
                "Cannot find 'Save to reading list' button",
                10);
        String folderForArticle = replaceTemplate(PATH_TO_READING_LIST_TPL, readingListName);
        this.waitForElementAndClick(
                folderForArticle,
                "Cannot find list for saved articles",
                10);
    }

}
