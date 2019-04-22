package testes.SITERAC;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import suporte.Generator;
import suporte.ScreenShot;
import java.util.concurrent.TimeUnit;



public class incluirParceiro {

    private WebDriver navegador;
//    private String link = "http://hml-siterac.unidas.com.br";
    private String link ="https://hml-siterac.unidas.com.br";
    private String lojaRetirada = "SAOP";
//    private String descLojaRetirada = "PAULISTA, SÃO PAULO, SAO PAULO";
    private String descLojaRetirada = "PAULISTA, SAO PAULO, SAO PAULO";
    private String horaRet = "12:00";
    private String horaDev = "11:00";
    private String docCli = "469.772.818-12";
    private String docConsulta = "46977281812";
    private String nomeCli = "Luis Carlos Maure Liberatti";
    private String emailCli = "luis.liberati@bliteti.com.br";
    private String telCli = "11949041329";
    private String codigoLatam = "123456";
    private String numMultiplus = "123456789012";
    private String numReserva = "";
    private String nomeArquivo="";
    private int contador=0;

    //Preencher dia mes e ano das datas de Retirada, devolução, data de retirada na edição, data de devolução na edição.
    private String diaRet="18", mesRet="03", anoRet="2019" ;
    private String diaDev="20", mesDev="03", anoDev="2019";
    private String diaRetAlt="18", mesRetAlt="03", anoRetAlt="2019" ;
    private String diaDevAlt="22", mesDevAlt="03", anoDevAlt="2019";



    @Rule
    public TestName test = new TestName();


    @Before
    public void setUp() {
        //abrindo o chromedriver e o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        //  Navegando para a pagina do taskit.
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.manage().window().maximize();
        navegador.get(link);
    }

    @After
    public void tearDown(){
        navegador.close();

    }

    @Test
    public void reservaParceiro() throws InterruptedException {

        incluirParceiro();
        consultarReserva();
        alterarReserva();
        consultarReserva();
        cancelarReserva();

    }

    public void incluirParceiro() throws InterruptedException{

        //clicar no menu Parcerias e Ofertas
        navegador.findElement(By.linkText("Parcerias e Ofertas")).click();
        //clicar em mais detalhes
        navegador.findElement(By.xpath("//a[@href=\"/para-voce/parceiros/latam/\"]")).click();
        navegador.findElement(By.xpath("//button[@title=\"Reserve aqui\"]")).click();
        navegador.findElement(By.id("InitialStoreOut")).sendKeys(lojaRetirada);
        String buscaRetirada = "//div[text()=\"" + descLojaRetirada + "\"]";
        navegador.findElement(By.xpath(buscaRetirada)).click();

        //preencher datas com js
        //js.executeScript("document.getElementById('DateOut').setAttribute('value','18/03/2019')");
        JavascriptExecutor js = (JavascriptExecutor) navegador;
        js.executeScript("document.getElementById('DateOut').setAttribute('data-value', '"+ anoRet+"/"+ mesRet+"/"+diaRet+"')");
        js.executeScript("document.getElementById('DateOut').setAttribute('value','"+diaRet+"/"+mesRet+"/"+anoRet+"')");

        js.executeScript("document.getElementById('DateDev').setAttribute('data-value', '"+ anoDev+"/"+ mesDev+"/"+diaDev+"')");
        js.executeScript("document.getElementById('DateDev').setAttribute('value','"+diaDev+"/"+mesDev+"/"+anoDev+"')");


        //selecionar horario
        navegador.findElement(By.id("HourOut")).click();
        navegador.findElement(By.xpath("//div[@class=\"dropdown-ui__menu dropdown-ui dropdown-ui--timepicker initial-hour-out active\"]//div[@class=\"dropdown-ui__body\"]//li[@data-value=\""+horaRet+"\"]")).click();
        navegador.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        navegador.findElement(By.id("HourDev")).click();
        navegador.findElement(By.xpath("//div[@class=\"dropdown-ui__menu dropdown-ui dropdown-ui--timepicker initial-hour-dev active\"]//div[@class=\"dropdown-ui__body\"]//li[@data-value=\""+horaDev+"\"]")).click();
        Thread.sleep(10000);
        //clicar em submit //button[@title="Alugue um Carro"]
        navegador.findElement(By.xpath("//button[@title=\"Aluguel de Carros -\"]")).click();


        //Passo 2.

        Thread.sleep(2500);
        //Selecionar Grupo AM
        navegador.findElement(By.xpath("//div[@id=\"car-groups\"]//a[@title=\"Selecionar Grupo B\"]")).click();


        //Passo 3

        Thread.sleep(2500);

        navegador.findElement(By.xpath("//button[@title=\"Avançar\"]")).click();


        //Passo 4

        navegador.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        navegador.findElement(By.id("input-cpf-passport")).sendKeys(docCli);
        navegador.findElement(By.id("fullname")).sendKeys(nomeCli);
        navegador.findElement(By.id("email")).sendKeys(emailCli);
        navegador.findElement(By.id("phone")).sendKeys(telCli);
        navegador.findElement(By.id("latamReservationCode")).sendKeys(codigoLatam);
        navegador.findElement(By.id("multiplusNumber")).sendKeys(numMultiplus);

        finalizarReserva();
        validarSucesso();

        //pegar número da reserva
        numReserva = navegador.findElement(By.xpath("//div[@class=\"booking-number\"]//span[@class=\"number\"]")).getText();

        JavascriptExecutor executor = (JavascriptExecutor)navegador;
        executor.executeScript("document.body.style.zoom = '0.75'");
        executor.executeScript("scrollBy(0,275)", "");
        nomeArquivo = "C:\\TesteAutomatizado\\RACunidas\\ReservaParceiro\\Latam" + Generator.dataHoraParaArquivo()+ test.getMethodName() + ".png";
        ScreenShot.tirar(navegador, nomeArquivo);
        Thread.sleep(1000);

    }

    public void finalizarReserva() throws InterruptedException{

        //clicar em aceitar termos
        navegador.findElement(By.xpath("//span[@class=\"label-text wrapper-click-here--terms-and-conditions--modal\"]")).click();
        //clicar em concluir
        navegador.findElement(By.xpath("//button[@class=\"step-next step-4-next button\"]")).click();
        navegador.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

    }

    public void  validarSucesso() throws InterruptedException{


        //Validar Sucesso.

        navegador.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        //validar que dentro do elemento com class me, possui ou está o texto "Hi, Julio".
        WebElement sucesso = navegador.findElement(By.xpath("//h1[@class=\"title\"]"));
        String textoNaClasseTitle=sucesso.getText();
        assertEquals("Reserva efetuada com sucesso.", textoNaClasseTitle);

    }


    public void consultarReserva() throws  InterruptedException{

        navegador.get(link+"/minhas-reservas");
        Thread.sleep(3000);
        navegador.findElement(By.id("input-cpf-passport")).sendKeys(docCli);
        // navegador.findElement(By.id("cpf-passport")).sendKeys(docConsulta);
        navegador.findElement(By.id("reservation-number")).sendKeys(numReserva);
        while (contador < 1) {
            nomeArquivo = "C:\\TesteAutomatizado\\RACunidas\\ReservaParceiro\\Latam" + Generator.dataHoraParaArquivo() + test.getMethodName() + "minhas_reservas.png";
            ScreenShot.tirar(navegador, nomeArquivo);
            Thread.sleep(1500);
            contador = contador + 1;
        }

        navegador.findElement(By.xpath("//input[@value=\"Consultar\"]")).click();
    }

    public void cancelarReserva() throws InterruptedException{

        navegador.findElement(By.linkText("CANCELAR RESERVA")).click();

        Thread.sleep(4000);
        navegador.findElement(By.xpath("//div[@class=\"modal-content\"]//div[@class=\"body--cancel-reservation__inner\"]//form//input[@id=\"2\"]/ancestor::label//span")).click();
        navegador.findElement(By.xpath("//a[@title=\"Cancelar reserva\"]")).click();
        Thread.sleep(1000);
        nomeArquivo = "C:\\TesteAutomatizado\\RACunidas\\ReservaParceiro\\LATAM" + Generator.dataHoraParaArquivo()+ test.getMethodName() + "cancelada.png";
        ScreenShot.tirar(navegador, nomeArquivo);

        navegador.findElement(By.linkText("FECHAR")).click();
        Thread.sleep(2000);
        WebElement mensagemHome = navegador.findElement(By.xpath("//h1[@class=\"title\"]"));
        String mensagem = mensagemHome.getText();
        assertEquals("Alugue um carro",mensagem);
        // assertEquals("Aluguel de Carros",mensagem);
    }

    public void alterarReserva() throws InterruptedException{

        JavascriptExecutor executor = (JavascriptExecutor)navegador;
        executor.executeScript("scrollBy(0,450)", "");
        nomeArquivo = "C:\\TesteAutomatizado\\RACunidas\\ReservaParceiro\\LATAM" + Generator.dataHoraParaArquivo()+ test.getMethodName() + "tela_minhasReservas.png";
        ScreenShot.tirar(navegador, nomeArquivo);

        //clicar para alterar o passo 1
        navegador.findElement(By.xpath("//div[@class=\"col-auto hidden-xs-down col--check-reservations--edit\"]//div[@onclick=\"AlterStep('"+ docConsulta + "', " +numReserva + ", 1, 282);\"]//a[@class=\"button--check-reservations--edit button--edit medium secondary\"]")).click();
        //Alterar Passo 1 mudando o periodo da reserva

        Thread.sleep(3500);
        JavascriptExecutor js = (JavascriptExecutor) navegador;
        js.executeScript("document.getElementById('DateOut').setAttribute('data-value', '"+ anoRetAlt+"/"+ mesRetAlt+"/"+diaRetAlt+"')");
        js.executeScript("document.getElementById('DateOut').setAttribute('value','"+diaRetAlt+"/"+mesRetAlt+"/"+anoRetAlt+"')");

        js.executeScript("document.getElementById('DateDev').setAttribute('data-value', '"+ anoDevAlt+"/"+ mesDevAlt+"/"+diaDevAlt+"')");
        js.executeScript("document.getElementById('DateDev').setAttribute('value','"+diaDevAlt+"/"+mesDevAlt+"/"+anoDevAlt+"')");
        Thread.sleep(10000);
        navegador.findElement(By.xpath("//button[@type=\"submit\"]")).click();
        Thread.sleep(2000);
        navegador.findElement(By.linkText("OK")).click();

        //Alterar Passo 2.
        Thread.sleep(4500);
        //Selecionar Grupo B
        navegador.findElement(By.xpath("//div[@id=\"car-groups\"]//a[@title=\"Selecionar Grupo B\"]")).click();
        navegador.findElement(By.linkText("OK")).click();
        //Passo3
        Thread.sleep(3500);
        navegador.findElement(By.xpath("//button[@title=\"Avançar\"]")).click();
        navegador.findElement(By.linkText("OK")).click();
        //Finalizar Reserva
        finalizarReserva();
        navegador.findElement(By.linkText("OK")).click();
        validarSucesso();

    }
}

