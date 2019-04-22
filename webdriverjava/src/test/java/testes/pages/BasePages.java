package testes.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testes.Web;

public class BasePages {
    protected WebDriver navegador;

    public BasePages(WebDriver navegador){
        this.navegador=navegador;
    }

    public String capturarTextoToast(){
      return  navegador.findElement(By.id("toast-container")).getText();
    }
}
