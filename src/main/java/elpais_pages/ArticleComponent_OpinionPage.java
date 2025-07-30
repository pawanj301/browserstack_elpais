package elpais_pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class ArticleComponent_OpinionPage {

	@FindBy(xpath = ".//header/h2/a")
    private WebElement headerLink;

    @FindBy(xpath = ".//p")
    private WebElement content;
    
    @FindBy(xpath = ".//figure/a/img")
    private List<WebElement> images;

    public ArticleComponent_OpinionPage(WebElement rootElement) {
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }

    public String getHeaderText() {
        return headerLink.getText();
    }

    public String getContentText() {
        return content.getText();
    }
    
    public String getImageUrl() {
    	if (!images.isEmpty() && images.get(0).isDisplayed()) {
            return images.get(0).getAttribute("src");
        } else {
            return null;
        }
    }
}
