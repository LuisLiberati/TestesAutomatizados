package testes.UNIDAS_CR;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Reserva {

    private WebDriver navegador;
    private String docCli= "46977281812";
    private String nomeCli="Luis Carlos Maure Liberatti";
    private String contato="Luis";
    private String emailCli="luis.liberati@bliteti.com.br";
    private String telCli="11949041329";

    private String tarifa= "128451";

    private String lojaRetirada = "SAOP";
    private String lojaDevolucao = "CGH";
    private String dataRetirada = "04/03/2019";
    private String dataDevolucao = "05/03/2019";
    private String horaRet="12:00";
    private String horaDev="11:00";
    private String link = "http://172.20.1.179/#/login";
    private String login="46977281812";
    private String passwd="123";
    private String numeroReserva="";
    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp() throws InterruptedException {
        //abrindo o chromedriver e o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Luis\\drivers\\chromedriver.exe");
        navegador = new ChromeDriver();

        //  Navegando para a pagina do taskit.
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.manage().window().maximize();
        navegador.get(link);
        //navegador.get("http://preprod-cr.unidas.com.br");
        //login
        navegador.findElement(By.id("cpf")).sendKeys(login);
        //navegador.findElement(By.id("cpf")).sendKeys("38454275827");
        //navegador.findElement(By.id("password")).sendKeys("nigo@0910");
        navegador.findElement(By.id("password")).sendKeys(passwd);
        navegador.findElement(By.xpath("//button[@class=\"btn btn-primary btn-block\"]")).click();

        Thread.sleep(3000);
        //validar login
        WebElement logado = navegador.findElement(By.className("toast-message"));
        String textoNaClasseMe =logado.getText();
        assertEquals("Bem-vindo! Autenticação realizada com sucesso!", textoNaClasseMe);

    }

    @Test
    public void ReservaCR() throws InterruptedException {
        Thread.sleep(5000);
        //Preencher DADOS DO CLIENTE
        navegador.findElement(By.xpath("//div[@class=\"col-md-3 items-nav ng-scope\"]//a[@href=\"#/reserva\"]")).click();
        navegador.findElement(By.id("document")).sendKeys(docCli);
        Thread.sleep(4000);
        navegador.findElement(By.id("contact")).sendKeys(contato);
        //Clicar em Próximo Dados do Cliente
        navegador.findElement(By.xpath("//form[@name=\"formClient\"]//button[@ng-click=\"saveSection($event)\"]")).click();

        //Preencher TARIFA
        Thread.sleep(4000);
        navegador.findElement(By.id("code-quote")).sendKeys(tarifa);
        Thread.sleep(3500);
        //Clicar em Próximo Tarifa
        navegador.findElement(By.xpath("//form[@name=\"formRate\"]//button[@ng-click=\"saveSection($event)\"]")).click();


        //Preencher dados de LOCAÇÃO
        //loja ret id = inputStoreWithdraw_value
        Thread.sleep(4000);
        WebElement lojaRet = navegador.findElement(By.id("inputStoreWithdraw_value"));
        lojaRet.sendKeys(lojaRetirada);
        Thread.sleep(1000);
        lojaRet.sendKeys( Keys.TAB);
        // data ret id =  date-withdraw
       // navegador.findElement(By.id("date-withdraw")).click();
        navegador.findElement(By.id("date-withdraw")).clear();
        navegador.findElement(By.id("date-withdraw")).sendKeys(dataRetirada);
        //hora ret id = timeHourDevolution
        navegador.findElement(By.xpath("//select[@name=\"time-with-hour\"]")).click();
        String horarioRetirada = "//select[@name=\"time-with-hour\"]//option[@label=\""+ horaRet + "\"]";
        navegador.findElement(By.xpath(horarioRetirada)).click();

        //loja dev id = inputStoreDevolution_value
        WebElement lojaDev = navegador.findElement(By.id("inputStoreDevolution_value"));
        lojaDev.sendKeys(lojaDevolucao);
        Thread.sleep(1000);
        lojaDev.sendKeys( Keys.TAB);
        // data dev id = date-devolution
       // navegador.findElement(By.id("date-devolution")).click();
        navegador.findElement(By.id("date-devolution")).clear();
        navegador.findElement(By.id("date-devolution")).sendKeys(dataDevolucao);
        // hora dev id = time-dev-hour
        navegador.findElement(By.xpath("//select[@name=\"time-dev-hour\"]")).click();
        String horarioDev = "//select[@name=\"time-dev-hour\"]//option[@label=\""+ horaDev + "\"]";
        navegador.findElement(By.xpath(horarioDev)).click();
        //Clicar em Próximo LOCAÇÃO
        navegador.findElement(By.xpath("//form[@name=\"formStore\"]//button[@ng-click=\"saveSection($event)\"]")).click();
        Thread.sleep(6000);

        //Selecionar Grupo de Veículo.
        navegador.findElement(By.xpath("//input[@value=\"AM \"]/ancestor::label//span[@class=\"uppercase ng-binding\"]")).click();
        navegador.findElement(By.xpath("//div[@class=\"card-body accordion__content active\"]//button[@class=\"btn btn-primary\"]")).click();
        //Selecionar proteção Parcial
        ////span[contains(.,'PROTEÇÃO PARCIAL')]
        Thread.sleep(3000);
        navegador.findElement(By.xpath("//span[contains(.,'PROTEÇÃO PARCIAL')]")).click();
        navegador.findElement(By.xpath("//div[@id=\"box-protection\"]//button[@class=\"btn btn-primary\"]")).click();
        Thread.sleep(3000);

        //Selecionar GPS
        navegador.findElement(By.xpath("//span[contains(.,'GPS')]")).click();
        navegador.findElement(By.xpath("//div[@class=\"col-md-12 form-group box-accessories accordion active\"]//button[@class=\"btn btn-primary\"]")).click();

        //Cliente também é o condutor.
        Thread.sleep(4000);
        navegador.findElement(By.xpath("//span[contains(.,'Cliente também é o condutor')]")).click();
        navegador.findElement(By.xpath("//form[@name=\"formConductor\"]//button[@class=\"btn btn-primary\"]")).click();

        //clicar em reservar
        Thread.sleep(4000);

        navegador.findElement(By.xpath("//button[contains(.,'RESERVAR')]")).click();

        Thread.sleep(4000);

        //validar sucesso.
        ////reserve-search-visualization//h2
        WebElement msgSucesso = navegador.findElement(By.xpath("//reserve-search-visualization//h2"));
        String textoSucesso =msgSucesso.getText();
        assertEquals("Confirmação de Reserva", textoSucesso);

        //pegar numero da reserva
        numeroReserva= navegador.findElement(By.xpath("//reserve-search-visualization//span[@class=\"num-protocol ng-binding\"]")).getText();

    }


}


//contains(.,'Model')]
////span[contains(.,'PROTEÇÃO PARCIAL')]