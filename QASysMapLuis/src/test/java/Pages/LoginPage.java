package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static junit.framework.TestCase.assertEquals;

public class LoginPage extends BasePages {
    public LoginPage(WebDriver navegador) {
        super(navegador);
    }
    public LoginPage preencherEmail(String email){
        navegador.findElement(By.id("user_email")).sendKeys(email);
        return this;
    }

    public  LoginPage preencherSenha(String senha){
        navegador.findElement(By.id("user_password")).sendKeys(senha);
        return this;

    }

    public LoginPage clicarEntrar() throws InterruptedException {
        navegador.findElement(By.xpath("//input[@id=\"btn-signin\"]")).click();
        Thread.sleep(2000);
        return this;
    }

    public LoginPage realizarLogin(String email, String senha) throws InterruptedException {
        preencherEmail(email);
        preencherSenha(senha);
        clicarEntrar();
        validarNaoLogin();


        return this;
    }

    public  LoginPage validarNaoLogin() {
        String mensagem= navegador.findElement(By.xpath("//li[@class=\"notifications-error__list-item\"]")).getText();
        assertEquals ("Invalid email or password.", mensagem);
        return this;
    }
}
