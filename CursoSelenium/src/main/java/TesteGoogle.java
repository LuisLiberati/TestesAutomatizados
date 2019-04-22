import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {

	@Test
	public void teste(){
//    	System.setProperty("webdriver.gecko.driver", "C:\\drivers/geckodriver.exe");
//		WebDriver  driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "C://drivers/chromedriver.exe");
		WebDriver  driver = new ChromeDriver();
		
		driver.manage().window().setPosition(new Point(1500,100)); //rodar na segunda tela.
		driver.manage().window().maximize();
		
		driver.get("http://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
		
		driver.quit();//fecha todas janelas do browser
//		driver.close();//fecha somente a janela aberta
		
	}
}
