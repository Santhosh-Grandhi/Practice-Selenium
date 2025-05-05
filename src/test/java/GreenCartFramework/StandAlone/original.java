package GreenCartFramework.StandAlone;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import GreenCartFramework.PageObjects.CartPage;
import GreenCartFramework.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class original {
	String dealCode = "rahulshettyacademy";
	String requiredItem = "Capsicum";
	
	@Test
	public void TenderOrder() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		List<WebElement> products = driver.findElements(By.cssSelector(".product-name"));
		products.stream().filter(s->s.getText().equalsIgnoreCase(requiredItem)).map(s->s.findElement(By.xpath("ancestor::div[@class='product']"))).forEach(s->s.findElement(By.xpath(".//div[@class='product-action']/button")).click());
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".added"))));
	    driver.findElement(By.cssSelector("img[alt=\"Cart\"]")).click();
	    driver.findElement(By.xpath("//button[text()=\"PROCEED TO CHECKOUT\"]")).click();
	    String cartProduct = driver.findElement(By.cssSelector(".cartTable tbody p.product-name")).getText();
	    Assert.assertEquals(cartProduct, requiredItem);
		driver.findElement(By.cssSelector(".promoCode")).sendKeys(dealCode);
		driver.findElement(By.cssSelector(".promoBtn")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".promoInfo"))));
		Assert.assertEquals(driver.findElement(By.cssSelector(".promoInfo")).getText(), "Code applied ..!");
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("select")));
		Select select = new Select(driver.findElement(By.tagName("select")));
		select.selectByValue("India");
		driver.findElement(By.cssSelector(".chkAgree")).click();
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();
		Boolean orderConfirmation = driver.findElement(By.cssSelector(".wrapperTwo")).isDisplayed();
		Assert.assertTrue(orderConfirmation);
	}
}
