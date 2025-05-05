package GreenCartFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import GreenCartFramework.AbstractMethods.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".product-name")
	List<WebElement> getItems; 
	
	@FindBy(css=".added")
	WebElement itemAdded;
	
	@FindBy(css="img[alt=\"Cart\"]")
	WebElement cartItems;
	
	@FindBy(xpath="//button[text()=\"PROCEED TO CHECKOUT\"]")
	WebElement cartPage;
	
    By parent = By.xpath("ancestor::div[@class='product']");
	
	By button = By.xpath(".//div[@class='product-action']/button");
	
	public List<WebElement> getProducts()
	{
		return getItems;
	}
	
	public WebElement VerifyItem(String requiredItem)
	{
		WebElement itemToBeAdded = getProducts().stream().filter(s->s.getText().equalsIgnoreCase(requiredItem)).findFirst().orElse(null);
		return itemToBeAdded;
	}
	
	public void AddItemtoCart(String requiredItem)
	{
		WebElement item = VerifyItem(requiredItem);
		WebElement parentTag = item.findElement(parent);
		parentTag.findElement(button).click();	
	}
	
	public CartPage goToCartPage()
	{
		WaitForWebElementToAppear(itemAdded);
	    cartItems.click();
	    cartPage.click();
	    return new CartPage(driver);
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	}


}
