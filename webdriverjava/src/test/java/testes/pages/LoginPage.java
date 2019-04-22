package testes.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePages{

    public LoginPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginFormPage clickSignIn(){
        // clicar no link que possui o texto sign in
        navegador.findElement(By.linkText("Sign in")).click();

        return new LoginFormPage(navegador);
    }
}
