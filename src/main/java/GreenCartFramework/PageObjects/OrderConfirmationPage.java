package GreenCartFramework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import GreenCartFramework.AbstractMethods.AbstractComponents;

public class OrderConfirmationPage extends AbstractComponents {

	WebDriver driver;
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	
//	driver.findElement(By.tagName("select"))
	@FindBy(css="select")
	WebElement dropdownSelection;
	
	//driver.findElement(By.cssSelector(".chkAgree")).click();
	@FindBy(css=".chkAgree")
	WebElement checkbox;
	
	//driver.findElement(By.xpath("//button[text()='Proceed']")).click();
	@FindBy(xpath="//button[text()='Proceed']")
	WebElement submit;
	
//	driver.findElement(By.cssSelector(".wrapperTwo"))
	@FindBy(css=".wrapperTwo")
	WebElement confirmationMessage;
	
	public void selectCountry(String country)
	{
		WaitForWebElementToAppear(dropdownSelection);
		Select select = new Select(dropdownSelection);
		select.selectByValue(country);
	}
	
	public boolean submitOrder()
	{
		checkbox.click();
		submit.click();
		return confirmationMessage.isDisplayed();
	}

}
