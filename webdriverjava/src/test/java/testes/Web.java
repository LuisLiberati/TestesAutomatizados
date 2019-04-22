package testes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Web {
    public static WebDriver createChrome(){
        //abrindo o chromedriver e o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Luis\\drivers\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();

        //  Navegando para a pagina do taskit.
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.get("http://hml-siterac.unidas.com.br");

        return navegador;
    }
}
