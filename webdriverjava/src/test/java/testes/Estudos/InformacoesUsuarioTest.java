package testes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {

    private  WebDriver navegador;




    @Before
    public void setUp(){
        //abrindo o chromedriver e o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Luis\\drivers\\chromedriver.exe");
        navegador = new ChromeDriver();

        //  Navegando para a pagina do taskit.
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.get("http://www.juliodelima.com.br/taskit");
    }

    @After
    public void tearDown(){
        navegador.close();

    }

    @Test
    public void testAdicionarUmaInforcacaoAdicionalDoUsuario(){



        // clicar no link que possui o texto sign in
        navegador.findElement(By.linkText("Sign in")).click();

        //indentificando o formulario de login que está dentro do formulario de id signinbox
        WebElement formularioSingInBox = navegador.findElement(By.id("signinbox"));

        // digitar dentro do campo login o texto julio0001
        formularioSingInBox.findElement(By.name("login")).sendKeys("julio001");

        // digitar no campopasswrod a senhs 123456.
        formularioSingInBox.findElement(By.name("password")).sendKeys("123456");

        // clicar no link com o texto SIGN IN
        navegador.findElement(By.linkText("SIGN IN")).click();

        //validar que dentro do elemento com class me, possui ou está o texto "Hi, Julio".
        WebElement me = navegador.findElement(By.className("me"));
        String textoNaClasseMe =me.getText();
        assertEquals("Hi, Julio", textoNaClasseMe);


    }

}
