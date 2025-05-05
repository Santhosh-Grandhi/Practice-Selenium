package GreenCartFramework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GreenCartFramework.AbstractMethods.AbstractComponents;

public class CartPage extends AbstractComponents {
    WebDriver driver;
    
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.cssSelector(".cartTable tbody p.product-name")).getText();
	@FindBy(css=".cartTable tbody p.product-name")
	WebElement cartProduct;
	
	//driver.findElement(By.cssSelector(".promoCode")).sendKeys(dealCode);
	@FindBy(css=".promoCode")
	WebElement deal;
	
	//driver.findElement(By.cssSelector(".promoBtn")).click();
	@FindBy(css=".promoBtn")
	WebElement applyDeal;
	
	//driver.findElement(By.cssSelector(".promoInfo"))
	@FindBy(css=".promoInfo")
	WebElement getMessage;
	
	//driver.findElement(By.xpath("//button[text()='Place Order']"))
	@FindBy(xpath="//button[text()='Place Order']")
	WebElement placeOrder;
	
	public String getCartItem()
	{
		return cartProduct.getText();
	}
	
	public void applyDealCode(String dealCode)
	{
		deal.sendKeys(dealCode);
		applyDeal.click();
	}
	
	public String verifyDeal()
	{
		WaitForWebElementToAppear(getMessage);
		return getMessage.getText();
	}
	
	public OrderConfirmationPage placeOrder()
	{
		placeOrder.click();
		return new OrderConfirmationPage(driver);
	}
	

}
