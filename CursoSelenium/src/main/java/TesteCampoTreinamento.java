import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class TesteCampoTreinamento {
	@Test
	public void teste() {
		System.setProperty("webdriver.chrome.driver", "C://drivers/chromedriver.exe");
		WebDriver  driver = new ChromeDriver();
		
		driver.manage().window().setPosition(new Point(1500,100)); //rodar na segunda tela.
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"); //abrir arquivo local do projeto, evita que eu tenha um arquivo que outra pessoa nao possua, obs o arquivo deve estar dentro de resources
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Luis Carlos");
		Assert.assertEquals("Luis Carlos",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));		 //getAttribute pega o valor digitado em algum campo.
		
		
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Estacionamento");
		Assert.assertEquals("Estacionamento",driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));	
		
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
	
		
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade")); 
		Select combo= new Select(element);
		combo.selectByIndex(2); //pega combo por posição
		combo.selectByValue("superior"); //pega pelo value
		combo.selectByVisibleText("2o grau incompleto"); //pega pelo que está visivel para o usuário (+recomendado).
		Assert.assertEquals("2o grau incompleto", combo.getFirstSelectedOption().getText()); // compara com o valor selecionado da combo
		
	
	}
	
	@Test
	public void validarCombo() {
		System.setProperty("webdriver.chrome.driver", "C://drivers/chromedriver.exe");
		WebDriver  driver = new ChromeDriver();
		
		driver.manage().window().setPosition(new Point(1500,100)); //rodar na segunda tela.
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"); //abrir arquivo local do projeto, evita que eu tenha um arquivo que outra pessoa nao possua, obs o arquivo deve estar dentro de resources
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade")); 
		Select combo= new Select(element);
		List<WebElement> options = combo.getOptions();  //usar o Ctrl + 1 para poder importar a list
		Assert.assertEquals(8, options.size());  //valida a quantidade de indices no combo.
		
		
		
		// Valida todos os indices, e vereifica se tem a opção mestrado.
		boolean encontrou = false;
		
		for (WebElement option:options) {
			if(option.getText().equals("Mestrado")) {
				
				encontrou = true;
				
				break;
			}
		}
		Assert.assertTrue(encontrou);
		
		
	}
	
	
	

	@Test
	public void ValidarComboMultiplaEscolha() {
		System.setProperty("webdriver.chrome.driver", "C://drivers/chromedriver.exe");
		WebDriver  driver = new ChromeDriver();
		
		driver.manage().window().setPosition(new Point(1500,100)); //rodar na segunda tela.
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"); //abrir arquivo local do projeto, evita que eu tenha um arquivo que outra pessoa nao possua, obs o arquivo deve estar dentro de resources
		WebElement element = driver.findElement(By.id("elementosForm:esportes")); 
		Select combo= new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("O que eh esporte?");
		combo.selectByVisibleText("Corrida");
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();  //cria uma lista para saber a quantidade de opções marcadas
		Assert.assertEquals(3,allSelectedOptions.size() );    // a variavel allSelectedOptions tem a quantidade de opções selecionadas.
		
		
		combo.deselectByVisibleText("O que eh esporte?");
		
	    allSelectedOptions = combo.getAllSelectedOptions();  //cria uma lista para saber a quantidade de opções marcadas
		Assert.assertEquals(2,allSelectedOptions.size() );    // a variavel allSelectedOptions tem a quantidade de opções selecionadas.
		
		

	}
	
	
	
	@Test
	public void buscaTextonaPagina() {
		System.setProperty("webdriver.chrome.driver", "C://drivers/chromedriver.exe");
		WebDriver  driver = new ChromeDriver();
		
		driver.manage().window().setPosition(new Point(1500,100)); //rodar na segunda tela.
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"); //abrir arquivo local do projeto, evita que eu tenha um arquivo que outra pessoa nao possua, obs o arquivo deve estar dentro de resources
		
		
		
		System.out.println(driver.findElement(By.tagName("body")).getText());
		
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Karate"));
	}
	
	
	
	@Test
	public void alert() {
		System.setProperty("webdriver.chrome.driver", "C://drivers/chromedriver.exe");
		WebDriver  driver = new ChromeDriver();
		
		driver.manage().window().setPosition(new Point(1500,100)); //rodar na segunda tela.
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"); //abrir arquivo local do projeto, evita que eu tenha um arquivo que outra pessoa nao possua, obs o arquivo deve estar dentro de resources
		
		
		driver.findElement(By.id("alert")).click();
		Alert alert =	driver.switchTo().alert();
		Assert.assertEquals("Alert Simples", alert.getText());
		String texto = alert.getText();
		
		System.out.println(texto);
		
		
//		alert.accept();
//		

		
	}
	
	

	@Test
	public void alertConfirme() {
		System.setProperty("webdriver.chrome.driver", "C://drivers/chromedriver.exe");
		WebDriver  driver = new ChromeDriver();
		
		driver.manage().window().setPosition(new Point(1500,100)); //rodar na segunda tela.
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"); //abrir arquivo local do projeto, evita que eu tenha um arquivo que outra pessoa nao possua, obs o arquivo deve estar dentro de resources
		
		
		driver.findElement(By.id("confirm")).click();
		Alert alert =	driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alert.getText());
		String texto = alert.getText();
		
		System.out.println(texto);
		alert.dismiss();
	
		Assert.assertEquals("Negado", alert.getText()); //validar segunda mensagem
		String texto2 = alert.getText();
		System.out.println(texto2);
		alert.accept();
		
//		alert.accept();
//		

		
	}
	
	
}
	
