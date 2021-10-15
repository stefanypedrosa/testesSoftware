package testeDeSistema;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class REQ01MantemLivroTests {
	 private WebDriver driver;
	 private Map<String, Object> vars;
	 JavascriptExecutor js;
	 
	 @BeforeEach
	 public void setUp() {
	 System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
	 driver = new ChromeDriver();
	 driver.get("https://ts-scel-web.herokuapp.com/login");
	 driver.manage().window().maximize();
	 js = (JavascriptExecutor) driver;
	 vars = new HashMap<String, Object>();
	 }
	 
	 @AfterEach
	 public void tearDown() {
	 driver.quit();
	 }
	 
	 @Test
	 public void ct01cadastrarlivrocomsucesso() {
	 // dado que o livro não esta cadastrado
	 driver.findElement(By.name("username")).click();
	 driver.findElement(By.name("username")).sendKeys("jose");
	 driver.findElement(By.name("password")).sendKeys("123");
	 driver.findElement(By.cssSelector("button")).click();
	 driver.findElement(By.linkText("livros")).click();
	 espera();
	// quando o usuario cadastrar um livro
	 driver.findElement(By.id("isbn")).click();
	 driver.findElement(By.id("isbn")).sendKeys("1111");
	 driver.findElement(By.id("titulo")).click();
	 driver.findElement(By.id("titulo")).sendKeys("narnia");
	 driver.findElement(By.id("autor")).click();
	 driver.findElement(By.id("autor")).sendKeys("c. s. lewis");
	 driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
	 // entao apresenta as informacoes do livro
	 assertEquals("c. s. lewis", driver.findElement(By.cssSelector("td:nth-child(6)")).getText());
	 assertEquals(("Lista de livros"), driver.findElement(By.id("titulopagina")).getText());
	 assertEquals("https://ts-scel-web.herokuapp.com/sig/livros", driver.getCurrentUrl());
	 assertTrue(driver.getPageSource().contains("1111"));
	 // *********************************************************************************
	 // teardown - exclusao do registro
	 // *********************************************************************************
	 driver.findElement(By.linkText("Excluir")).click();
	 }
	 
	 @Test
	 public void ct02atualizarlivrocomsucesso() {
	 //***********************************************************************************
	 // dado que o livro esta cadastrado
	 //***********************************************************************************
	  driver.findElement(By.name("username")).click();
	  driver.findElement(By.name("username")).sendKeys("jose");
	  driver.findElement(By.name("password")).sendKeys("123");
	  driver.findElement(By.cssSelector("button")).click();
	  driver.findElement(By.linkText("livros")).click();
	  espera();
	  driver.findElement(By.id("isbn")).click();
	  driver.findElement(By.id("isbn")).sendKeys("1111");
	  driver.findElement(By.id("titulo")).sendKeys("narnia");
	  driver.findElement(By.id("autor")).sendKeys("c. s. lewis");
	  driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
	  espera();
	  assertEquals("c. s. lewis", driver.findElement(By.cssSelector("td:nth-child(6)")).getText());
	  //**********************************************************************************
	  // quando o usuario altera o titulo do livro
	  //**********************************************************************************
	  driver.findElement(By.linkText("Editar")).click();
	  driver.findElement(By.cssSelector(".form-group:nth-child(2)")).click();
	  driver.findElement(By.id("titulo")).clear();
	  driver.findElement(By.id("titulo")).sendKeys("as cronicas de narnia");
	  driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
	  // entao o sistema apresenta as informações do livro com o titulo alterado
	  assertTrue(driver.getPageSource().contains("as cronicas de narnia"));
	  //************************************************************************************
	  // teardown - exclusao do registro
	  // ***********************************************************************************
	  driver.findElement(By.linkText("Excluir")).click();
	 }
	 
	 @Test
	 public void ct03excluirlivrocomsucesso() {
	  // ******************************************************************************
	  // dado que o livro esta cadastrado
	  // ******************************************************************************
	  driver.findElement(By.name("username")).click();
	  driver.findElement(By.name("username")).sendKeys("jose");
	  driver.findElement(By.name("password")).sendKeys("123");
	  driver.findElement(By.cssSelector("button")).click();
	  driver.findElement(By.linkText("livros")).click();
	  espera();
	  driver.findElement(By.id("isbn")).click();
	  driver.findElement(By.id("isbn")).sendKeys("1111");
	  driver.findElement(By.id("titulo")).click();
	  driver.findElement(By.id("titulo")).sendKeys("narnia");
	  driver.findElement(By.id("autor")).click();
	  driver.findElement(By.id("autor")).sendKeys("c. s. lewis");
	  driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
	  espera();
	  assertEquals("c. s. lewis", driver.findElement(By.cssSelector("td:nth-child(6)")).getText());
	  //**********************************************************************************
	  // quando o usuario excluir o livro
	  //**********************************************************************************
	  driver.findElement(By.linkText("Excluir")).click();
	  driver.findElement(By.cssSelector(".form-group:nth-child(2)")).click();
	  driver.findElement(By.id("isbn")).clear();
	  driver.findElement(By.id("titulo")).clear();
	  driver.findElement(By.id("autor")).clear();
	  driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
	  // entao o sistema apresenta as informações do livro excluido com sucesso
	  //************************************************************************************
	  // teardown - exclusao do registro
	  // ***********************************************************************************
	  driver.findElement(By.linkText("Excluir")).click();
	 }
} 
