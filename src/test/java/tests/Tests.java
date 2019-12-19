package tests;

import java.util.ArrayList;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import helpers.Screenshooter;
import helpers.WebDriverManager;
import helpers.WebDriverManager.Tamanio;
import pages.PageLogin;
import pages.PageLogon;
import pages.PageReservation;

public class Tests {
	private WebDriver driver;
	
	//tabs es un array que contendra las ventanas abiertas del browser
	ArrayList<String> tabs;
	//codigo que se ejecuta antes de los tests
	@BeforeMethod
	public void setUp() {
		DesiredCapabilities caps= new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver= new ChromeDriver();
		
		//Tamaño del browser (la clase WebDriverManager la cree yo)
		WebDriverManager.setWindowSize(driver, Tamanio.MAXIMIZED);
		
		//Direcciono a la pagina principal
		driver.navigate().to("http://newtours.demoaut.com/");
		
		//codigo javascript en java
		JavascriptExecutor javascriptExecutor= (JavascriptExecutor) driver;
		String goolgeWindow= "window.open('http://www.google.com')";
		javascriptExecutor.executeScript(goolgeWindow);
		
		//registro las ventanas en mi lista tabs
		tabs= new ArrayList<String> (driver.getWindowHandles());
		
		//empiezo con un switch entre paginas, volviendo al principio en la proxima linea para que todo funcione correctamente.
		driver.switchTo().window(tabs.get(1)).navigate().to("http://www.youtube.com");
		driver.switchTo().window(tabs.get(0));
	}
	
	//Tests
	@Test
	public void pruebaUno() {
		PageLogin login= new PageLogin(driver);
		PageLogon logon= new PageLogon(driver);
		login.login_fields("user", "user");
		//login.login("user", "user");
		logon.assertLogonPage();
	}
	
	@Test
	public void pruebaDos() {
		PageLogin login= new PageLogin(driver);
		PageReservation reservation= new PageReservation(driver);
		login.login("mercury", "mercury");
		reservation.assertReservationPage();
	}

	@Test
	public void pruebaTres() {
		PageLogin login= new PageLogin(driver);
		PageReservation reservation = new PageReservation(driver);
		login.login("mercury", "mercury");
		reservation.selectPassengers(4);
		reservation.selectFromPort(2);
		reservation.selectToPort("London");
	}
	
	//Codigo que se ejecuta despues de los tests
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(!result.isSuccess()) {
			
			//sacando una screenshot (usar path relativos, por buena practica)
			Screenshooter.takeScreenshot("Error", driver);
		}
		
		//cierro ventanas
		for (int ventana=0; ventana< tabs.size(); ventana++) {
			driver.switchTo().window(tabs.get(ventana)).close();
		}
	}
	
}
