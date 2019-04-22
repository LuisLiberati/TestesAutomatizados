package testes;
import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import sun.java2d.loops.FillRect;
import suporte.Generator;
import suporte.ScreenShot;

import java.util.concurrent.TimeUnit;



@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "informacoesUsuarioTeste.csv")
public class inserirDadosUsuarioLogado {


    private  WebDriver navegador;

    @Before
    public void setUp(){
        navegador = Web.createChrome();

    }



    @After
    public void tearDown(){
        navegador.close();
    }



    @Test
    public void testAdicionarUmaInforcacaoAdicionalDoUsuario(@Param(name="tipo")String tipo, @Param(name="contato") String contato, @Param(name="mensagem")String mensagemdoArquivo) {


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

        //Clicar em um link que possui a class me
        navegador.findElement(By.className("me")).click();

        //Clicar no link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement((By.linkText("MORE DATA ABOUT YOU"))).click();

        //Clicar com o botão através do seu xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        //identificar a popup onde está o formulário de id addmoredata
        WebElement popupFormAddMoreData = navegador.findElement(By.id("addmoredata"));

        //na combo de name "type" escolher a opção Phone (usuário vê)
        WebElement campoType=  popupFormAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        //no campo de name contact, digitar +5511949041329
        popupFormAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        //Clicar no link de text "SAVE" que está na popup
        popupFormAddMoreData.findElement(By.linkText("SAVE")).click();

        //Na mensagem de id "toast-container", validar que o texto é: "Your contact has benn added!"
        WebElement mensagemPopup = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPopup.getText();
        assertEquals(mensagemdoArquivo,mensagem);
        ScreenShot.tirar(navegador, "Users\\Luis\\IdeaProjects\\webdriverjava\\src\\test\\java\\screenshots"+ Generator.dataHoraParaArquivo()+ "removerUmContatoDeUmUsuario.png");

    }
}
////input[@data-title="Cadeira de Bebê"]/ancestor::label//span[@class="in"]