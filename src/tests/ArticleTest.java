package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyReadingListPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTest extends CoreTestCase {

    @Test
    public void testSaveTwoArticles() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        MyReadingListPageObject myReadingListPageObject = new MyReadingListPageObject(driver);

        searchPageObject.skipOnboarding();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java");
        searchPageObject.openArticleWithDescription("Object-oriented programming language");
        articlePageObject.saveArticleToReadingList("Saved");
        articlePageObject.closeArticle();
        articlePageObject.doNotSyncReadingList();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.waitForSearchResult("Appium");
        searchPageObject.openArticleWithTitle("Appium");
        articlePageObject.saveArticleToReadingListAndCloseHint("Saved");
        articlePageObject.openMyReadingList();

        myReadingListPageObject.openReadingList("Saved");
        int countOfArticles = myReadingListPageObject.getCountOfArticles();
        myReadingListPageObject.deleteArticleByName("Appium");
        int countOfArticlesAfterSwipe = myReadingListPageObject.getCountOfArticles();
        assertEquals("Error in count of articles", countOfArticles - 1, countOfArticlesAfterSwipe);

        String titleOfArticleInSavedList = myReadingListPageObject.getNameOfArticle();
        myReadingListPageObject.openArticleByName(titleOfArticleInSavedList);
        String titleOfArticle = articlePageObject.getTitleOfArticle();
        assertEquals("Error in title of article", titleOfArticleInSavedList, titleOfArticle);
    }

}
