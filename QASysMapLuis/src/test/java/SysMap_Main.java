import Pages.Home;
import Pages.Interactions;
import Pages.LoginPage;
import Suporte.Web;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;


@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "login.csv")

public class SysMap_Main {
    private String link ="https://www.ultimateqa.com/automation/";
    private WebDriver navegador;

    @Before
    public void setUP(){
        navegador= Web.createChrome(link);

    }

    @Test
    public void testFluxo(
            @Param(name = "email") String email,
            @Param(name = "senha") String senha
    ) throws InterruptedException {
        Home home = new Home(navegador);
        home.selecionarOpcao();
        Interactions interaction = new Interactions(navegador);
        interaction.clicarID();
        interaction.clickThisLink();
        interaction.radioButton();
        interaction.checkBox();
        interaction.clicarTab1();
        interaction.goToLoginPage();
        LoginPage login= new LoginPage(navegador);
        login.realizarLogin(email,senha);






    }

}
