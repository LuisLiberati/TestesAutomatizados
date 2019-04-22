package testes;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.ScreenShot;

import java.util.concurrent.TimeUnit;

public class removerContato {

    private  WebDriver navegador;

    @Rule
    public TestName test = new TestName();




    @Before
    public void setUp(){
        //abrindo o chromedriver e o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Luis\\drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        //  Navegando para a pagina do taskit.
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.get("http://www.juliodelima.com.br/taskit");

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

    }

   /*@After
    public void tearDown(){
        navegador.close();
    }*/

    @Test
    public void removerDadosContato(){

        //clicar no elemento pelo xpath //span[text()="++5561995887766"]/following-sibling:: a
        navegador.findElement(By.xpath("//span[text()=\"+5561999554466\"]/following-sibling:: a")).click();

        //confirmar a janela javascrip
        navegador.switchTo().alert().accept();

        //validar que a mensagem apresentada foi Rest in peace, dear phone!
        WebElement mensagemPopup = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPopup.getText();
        assertEquals("Rest in peace, dear phone!",mensagem);

        String nomeArquivo = "C:\\TesteAutomatizado\\prints" + Generator.dataHoraParaArquivo()+ test.getMethodName() + ".png";
        ScreenShot.tirar(navegador, nomeArquivo);

        // esperar 10 segundos.
        WebDriverWait esperar = new WebDriverWait(navegador, 10);
        esperar.until(ExpectedConditions.stalenessOf(mensagemPopup));

        // fazer logout link com texto "Logout".
      //  navegador.findElement(By.linkText("Logout")).click();






    }

}
//span[text()=""]following-sibling:: a;
//tag[metodo()="valor"]/following-sibling:: tag do que quero encontrar depois disso.
//tag[metodo()="valor"]/preceding-sibling:: tag do que quero encontrar antes disso.