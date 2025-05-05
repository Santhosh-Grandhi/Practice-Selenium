package GreenCartFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import GreenCartFramework.PageObjects.CartPage;
import GreenCartFramework.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver InitiliazeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//GreenCartFramework//Resources//GlobalData.properties");
		prop.load(fis);	
		String browserName = prop.getProperty("browser");
		String appUrl = prop.getProperty("url");
		
		if(browserName.contains("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.contains("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browserName.contains("edge"))
		{
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonData() throws IOException
	{
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir"+"\\src\\test\\java\\GreenCartFramework\\Data\\ItemsData.json")), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		return data;
	}
	
	@BeforeMethod
	public LandingPage LaunchApplication() throws IOException
	{
		driver = InitiliazeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.getProducts();
		return landingPage;
	}

}
