package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import elpais_pages.ArticleComponent_OpinionPage;
import elpais_pages.HomePage;
import elpais_pages.OpinionPage;
import io.restassured.response.Response;
import payload.TranslationPayload;
import utils.FileDownload;
import utils.RequestBuilder;

public class ELPaisWorkflowTest extends BaseTest {
	
	private List<ArticleComponent_OpinionPage> articleList = new ArrayList<>();
    private Map<String, String> articleDetailsMap = new LinkedHashMap<>();
    private Map<String, String> translatedHeaders = new LinkedHashMap<>();
    private static final int MAX_ARTICLES = 5;

    @Test
    public void testHomePageLanguageIsSpanish() {
        HomePage homepage = new HomePage(BaseTest.getDriver());
        homepage.acceptCookiesOnHomePage();

        String language = homepage.getHomePageLanguage();
        Assert.assertEquals(language.toLowerCase(), "españa", "Language is not set to Spanish!");
        System.out.println("Website language verified as: " + language);
    }
    
    @Test(dependsOnMethods = "testHomePageLanguageIsSpanish")
    public void testFetchOpinionArticlesAndDownloadImages() {
        HomePage homepage = new HomePage(BaseTest.getDriver());
        homepage.acceptCookiesOnHomePage();
        homepage.navigateToOpinionTab();

        OpinionPage opinionPage = new OpinionPage(BaseTest.getDriver());
        articleList = opinionPage.getAllArticles();

        int limit = Math.min(MAX_ARTICLES, articleList.size());

        if (limit == 0) {
            throw new NullPointerException("No articles found in Opinion section.");
        }

        for (int i = 0; i < limit; i++) {
            ArticleComponent_OpinionPage article = articleList.get(i);
            String header = article.getHeaderText();
            String content = article.getContentText();
            String imageUrl = article.getImageUrl();

            articleDetailsMap.put(header, content);

            System.out.println("\nArticle " + (i + 1));
            System.out.println("Title: " + header);
            System.out.println("Content: " + content);

            if (imageUrl != null) {
                String filePath = "D:/article-cover-image" + (i + 1) + ".png";
                FileDownload.downloadImage(imageUrl, filePath);
                System.out.println("Image downloaded to: " + filePath);
            }
        }
    }
    
    @Test(dependsOnMethods = "testFetchOpinionArticlesAndDownloadImages")
    public void testTranslateArticleHeadersToEnglish() {
        for (String spanishHeader : articleDetailsMap.keySet()) {
            String body = TranslationPayload.build("es", "en", spanishHeader);

            Response response = RequestBuilder.getRequestSpec(body)
                    .when().post()
                    .then().statusCode(200)
                    .extract().response();

            String translated = response.jsonPath().getString("[0]");
            translatedHeaders.put(spanishHeader, translated);

            System.out.println("\nTranslated Header:");
            System.out.println("ES: " + spanishHeader);
            System.out.println("EN: " + translated);
        }
    }
    
    @Test(dependsOnMethods = "testTranslateArticleHeadersToEnglish")
    public void testRepeatedWordsInTranslatedHeaders() {
        Map<String, Integer> wordCountMap = new HashMap<>();
        Pattern wordPattern = Pattern.compile("\\b\\w+\\b");

        for (String translatedHeader : translatedHeaders.values()) {
            wordPattern.matcher(translatedHeader.toLowerCase()).results()
                    .map(m -> m.group())
                    .forEach(word -> wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1));
        }

        System.out.println("\nWords repeated more than twice:");
        wordCountMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 2)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}