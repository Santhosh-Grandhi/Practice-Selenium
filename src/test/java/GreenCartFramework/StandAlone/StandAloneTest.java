package GreenCartFramework.StandAlone;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
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
import GreenCartFramework.PageObjects.OrderConfirmationPage;
import GreenCartFramework.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest extends BaseTest {
	String dealCode = "rahulshettyacademy";
	String requiredItem = "Capsicum";
	WebDriver driver;
	
	@Test
	public void TenderOrder() throws InterruptedException, IOException
	{
		landingPage.AddItemtoCart(requiredItem);
		CartPage cart = landingPage.goToCartPage();
	    String cartProduct = cart.getCartItem();
	    Assert.assertEquals(cartProduct, requiredItem);
	    cart.applyDealCode(dealCode);
	    String actualText = cart.verifyDeal();
		Assert.assertEquals(actualText, "Code applied ..!");
		OrderConfirmationPage orderConfirmation = cart.placeOrder();
		orderConfirmation.selectCountry("India");
		Boolean orderConfirmationText = orderConfirmation.submitOrder();
		Assert.assertTrue(orderConfirmationText);
	}
}
