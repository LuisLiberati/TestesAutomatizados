package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home extends BasePages {
    public Home(WebDriver navegador) {
        super(navegador);
    }

    public Interactions selecionarOpcao() throws InterruptedException {
        navegador.findElement(By.linkText("Interactions with simple elements")).click();
        Thread.sleep(2000);
        return new Interactions(navegador);

    }
}
