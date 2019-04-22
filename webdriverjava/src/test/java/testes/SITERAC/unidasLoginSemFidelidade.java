package testes.SITERAC;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import suporte.Generator;
import suporte.ScreenShot;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class unidasLoginSemFidelidade {

    private WebDriver navegador;
    JavascriptExecutor js = (JavascriptExecutor) navegador;
    String cpfDigitado = "469.772.818-12";

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp() {
        //abrindo o chromedriver e o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();

        //  Navegando para a pagina do taskit.
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.manage().window().maximize();
        navegador.get("https://unidas.com.br");
    }

    @Test
    public void LoginDoUsuarioSemFidelidade() {

        //clicar no link LOGIN
        navegador.findElement(By.linkText("LOGIN")).click();
        navegador.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        //navegador.findElement(By.id("login-cpf")).sendKeys(cpfDigitado);
        //digitar o cpf 46977281812 no campo de id"login-cpf" 39040475822
        navegador.findElement(By.id("login-cpf")).sendKeys("4");
        navegador.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        navegador.findElement(By.id("login-cpf")).sendKeys("6");
        navegador.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        navegador.findElement(By.id("login-cpf")).sendKeys("9");
        navegador.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        navegador.findElement(By.id("login-cpf")).sendKeys("7");
        navegador.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        navegador.findElement(By.id("login-cpf")).sendKeys("7");
        navegador.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        navegador.findElement(By.id("login-cpf")).sendKeys("2");
        navegador.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        navegador.findElement(By.id("login-cpf")).sendKeys("8");
        navegador.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        navegador.findElement(By.id("login-cpf")).sendKeys("1");
        navegador.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        navegador.findElement(By.id("login-cpf")).sendKeys("8");
        navegador.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        navegador.findElement(By.id("login-cpf")).sendKeys("1");
        navegador.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        navegador.findElement(By.id("login-cpf")).sendKeys("2");
        //  navegador.findElement(By.id("login-cpf")).sendKeys(cpfDigitado);
        //digitar no pin de id "login-pin-1" a senha 123456
        navegador.findElement(By.id("login-pin-1")).sendKeys("1");
        navegador.findElement(By.id("login-pin-2")).sendKeys("2");
        navegador.findElement(By.id("login-pin-3")).sendKeys("3");
        navegador.findElement(By.id("login-pin-4")).sendKeys("4");
        navegador.findElement(By.id("login-pin-5")).sendKeys("5");
        navegador.findElement(By.id("login-pin-6")).sendKeys("6");
        //clicar no botão de login id "submit-user-login"
        navegador.findElement(By.id("submit-user-login")).click();
        //validar se o cpf está logado no id "register-cpf" possui o value 46977281812
        navegador.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        //  String userLogado = js.executeScript("return document.querySelector('#register-cpf').value").toString();
        WebElement extrato= navegador.findElement(By.id("pills-seu-extrato-tab"));
        String textoExtrato= extrato.getText();
        assertEquals("Seu Extrato", textoExtrato);
        String nomeArquivo = "C:\\TesteAutomatizado\\RACunidas\\Login\\RAC" + Generator.dataHoraParaArquivo()+ test.getMethodName() + ".png";
        ScreenShot.tirar(navegador, nomeArquivo);
    }
}



