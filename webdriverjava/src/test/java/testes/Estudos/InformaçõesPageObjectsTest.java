package testes.Estudos;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import testes.Web;
import testes.pages.LoginPage;
import static org.junit.Assert.*;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "informacoesUsuarioTesteObjects.csv")

public class InformaçõesPageObjectsTest {

    private WebDriver navegador;

    @Before
    public void setUP(){
        navegador= Web.createChrome();
    }


    @Test
    public void testAdicionarUmaInforcacaoAdicionalDoUsuario(
            @Param(name="login")String login,
            @Param(name="senha")String senha,
            @Param(name="tipo")String tipo,
            @Param(name="contato")String contato,
            @Param(name="mensagemEsperada")String mensagemEsperada){
        String textoToast= new LoginPage(navegador)
                .clickSignIn()
                .fazerLogin(login,senha)
                .clicarMe()
                .clicarAbaMoreDataAboutYou()
                .clicarNoBotaoAddMoreDataAboutYou()
                .adicionarContato(tipo, contato)
                .capturarTextoToast();
        assertEquals(mensagemEsperada, textoToast);

    }


    @After
    public void tearDown(){

        //navegador.quit();
    }




}
