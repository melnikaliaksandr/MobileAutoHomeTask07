package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyReadingListPageObject extends MainPageObject {

    private static final String
            ARTICLE = "id:org.wikipedia:id/page_list_item_title",
            PATH_TO_ARTICLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']",
            PATH_TO_READING_LIST_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{SUBSTRING}']";

    public MyReadingListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public String getNameOfArticle() {
        return this.waitForElementAndGetAttribute(
                ARTICLE,
                "text",
                "Cannot get attribute text",
                10);
    }

    public void openArticleByName(String articleName) {
        String article = replaceTemplate(PATH_TO_ARTICLE_TPL, articleName);
        this.waitForElementAndClick(
                article,
                "Cannot click of article",
                10);
    }

    public void deleteArticleByName(String articleName) {
        String article = replaceTemplate(PATH_TO_ARTICLE_TPL, articleName);
        this.swipeElementToLeft(
                article,
                "Cannot swipe element to left");
    }

    private static String replaceTemplate(String template, String substring) {
        return template.replace("{SUBSTRING}", substring);
    }

    public int getCountOfArticles() {
        return this.waitAndReturnListOfElements(
                ARTICLE,
                "Cannot find list of articles",
                10).size();
    }

    public void openReadingList(String readingListName) {
        String readingList = replaceTemplate(PATH_TO_READING_LIST_TPL, readingListName);
        this.waitForElementAndClick(
                readingList,
                "Cannot find list for saved articles",
                10);
    }

}
