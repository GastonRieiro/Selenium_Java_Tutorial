package pages;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TakesScreenshot;

/*
 implicitWait espera x tiempo a que carge el DOM
 */
public class PageLogin {
	private WebDriver driver;
	private By userField;
	private By passwordField;
	private By loginButton;
	private By fields;
	public PageLogin(WebDriver driver){
		this.driver= driver;
		userField= By.name("userName");
		passwordField= By.name("password");
		loginButton= By.name("login");
		fields= By.tagName("input");
	}
	
	public void login(String user, String pass) {
		driver.findElement(userField).sendKeys(user);
		driver.findElement(passwordField).sendKeys(pass);
		driver.findElement(loginButton).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//login usando la lista de inputs: loginFields
	public void login_fields(String user, String pass) {
		List<WebElement> loginFields= driver.findElements(fields);
		loginFields.get(2).sendKeys(user);
		loginFields.get(3).sendKeys(pass);
		loginFields.get(4).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
}
