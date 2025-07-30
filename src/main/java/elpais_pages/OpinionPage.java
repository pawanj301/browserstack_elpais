package elpais_pages;

import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpinionPage {

	// Web Elements
	@FindBy(tagName = "article")
	private List<WebElement> articlesOpinionPage;

	private WebDriver driver;

	// Constructors
	public OpinionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions
	public List<ArticleComponent_OpinionPage> getAllArticles() {
        List<ArticleComponent_OpinionPage> articles = new ArrayList<>();
        for (WebElement article : articlesOpinionPage) {
            articles.add(new ArticleComponent_OpinionPage(article));
        }
        return articles;
    }
}
