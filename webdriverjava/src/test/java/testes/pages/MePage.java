package testes.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MePage extends BasePages {

    public MePage(WebDriver navegador) {
        super(navegador);
    }

    public MePage clicarAbaMoreDataAboutYou(){

        navegador.findElement((By.linkText("MORE DATA ABOUT YOU"))).click();
        return this;
    }

    public AddContactPage clicarNoBotaoAddMoreDataAboutYou(){
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
        return new AddContactPage(navegador);

    }
}


