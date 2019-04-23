package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static junit.framework.TestCase.assertEquals;

public class Interactions extends BasePages {
    public Interactions(WebDriver navegador) {
        super(navegador);

    }

    public Interactions clicarID() throws InterruptedException {
        navegador.findElement(By.id("idExample")).click();
        Thread.sleep(2000);
        String titulo = navegador.findElement(By.xpath("//h1[contains(.,'Button success')]")).getText();
        assertEquals("Button success", titulo);
        navegador.get("https://www.ultimateqa.com/simple-html-elements-for-automation/");
        Thread.sleep(2000);
        return this;
    }

    public Interactions clickThisLink() throws InterruptedException {
        navegador.findElement(By.linkText("Click this link")).click();
        Thread.sleep(2000);
        String titulo = navegador.findElement(By.xpath("//h1[contains(.,'Link success')]")).getText();
        assertEquals("Link success", titulo);
        navegador.get("https://www.ultimateqa.com/simple-html-elements-for-automation/");
        Thread.sleep(2000);
        return this;
    }



    public  Interactions radioButton() throws  InterruptedException{
        navegador.findElement(By.xpath("//form//input[@value=\"male\"]")).click();

        return this;
    }


    public  Interactions checkBox() throws  InterruptedException{
        navegador.findElement(By.xpath("//form//input[@value=\"Car\"]")).click();

        return this;
    }

    public Interactions openFirstToggle(){

        navegador.findElement(By.xpath("  //div[@class=\"et_pb_module et_pb_toggle et_pb_toggle_0 et_pb_toggle_item et_pb_toggle_open\"]//h5[contains(.,'Open toggle to read text')]")).click();
        return this;
    }

    public Interactions clicarTab1() throws InterruptedException {
        navegador.findElement(By.linkText("Tab 1")).click();
        Thread.sleep(1000);
        String titulo = navegador.findElement(By.xpath("//div[@class=\"et_pb_tab_content\"]")).getText();
        assertEquals("tab 1 content",titulo);
        navegador.findElement(By.xpath("//body")).sendKeys(Keys.F5);
        return this;
    }

    public LoginPage goToLoginPage() throws InterruptedException {
//        navegador.get("https://www.ultimateqa.com/simple-html-elements-for-automation/");
        navegador.findElement(By.xpath("//body")).sendKeys(Keys.F5);
        JavascriptExecutor js = (JavascriptExecutor) navegador;
        js.executeScript("scrollBy(0,-450)", "");
        Thread.sleep(2000);
        navegador.findElement(By.xpath("//*[@id=\"et-boc\"]/div/div[3]/div/div[3]/div[2]/div[2]/a")).click();

        //div[@id=\"page-container\"]//a[contains(.,'Go to login page')]"
        return new LoginPage(navegador);
    }
}
