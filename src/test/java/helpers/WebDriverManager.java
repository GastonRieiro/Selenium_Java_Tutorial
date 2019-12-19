package helpers;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;


public class WebDriverManager {
	
	public static enum Tamanio
	{
	    MAXIMIZED, FULLSCREEN
	}
	
	//modifica tamaño del browser
	public static void setWindowSize(WebDriver driver, Tamanio size) {
		switch(size) {
		case MAXIMIZED:
			driver.manage().window().maximize();
		case FULLSCREEN:
			driver.manage().window().fullscreen();
		}		
	}
	
	//modifica tamaño del browser
	public static void setWindowSize(WebDriver driver, int x, int y) {
		driver.manage().window().setSize(new Dimension(x, y));	
	}
	
	//modifica posicion del browser
	public static void setWindowPosition(WebDriver driver, int x, int y) {
		driver.manage().window().setPosition(new Point(400, 100));	
	}
}
