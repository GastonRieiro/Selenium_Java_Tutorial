package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/*
 ExplicitWait: espera a que cargue x elemento durante por n segundos.
 Requiere de un objeto WebDriverWait, utilizando el metdo 'until(ExpectedConditions.presenceOfElementLocated(%elemento%)'
 Dicho metodo devuelve un objeto de tipo WebElement
 */
public class PageReservation {
	private WebDriver driver;
	private By titleText;
	private By passengersDrop;
	private By fromDrop;
	private By toDrop;
	public PageReservation(WebDriver driver) {
		this.driver= driver;
		titleText= By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/font");
		passengersDrop= By.name("passCount");
		fromDrop= By.name("fromPort");
		toDrop= By.name("toPort");
	}
	
	public void assertReservationPage() {
		Assert.assertTrue(driver.findElement(titleText).getText().contains("Flight Finder to search"));
	}
	
	public void selectPassengers(int cant) {
		WebDriverWait wait= new WebDriverWait(driver, 10);
		WebElement cantPassengersElement= wait.until(ExpectedConditions.presenceOfElementLocated(passengersDrop));
		Select selectPassengers = new Select(cantPassengersElement);
		selectPassengers.selectByVisibleText(Integer.toString(cant));
	}
	
	public void selectFromPort(int index) {
		WebDriverWait wait= new WebDriverWait(driver, 10);
		WebElement fromDropElement= wait.until(ExpectedConditions.presenceOfElementLocated(fromDrop));
		Select selectFrom = new Select(fromDropElement);
		selectFrom.selectByIndex(index);
	}
	
	public void selectToPort(String city) {
		WebDriverWait wait= new WebDriverWait(driver, 10);
		WebElement toDropElement= wait.until(ExpectedConditions.presenceOfElementLocated(toDrop));
		Select selectFrom = new Select(toDropElement);
		selectFrom.selectByValue(city);
	}
	
	
}