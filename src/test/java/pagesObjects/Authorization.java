package pagesObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class Authorization {
    public WebDriver driver;

    public Authorization(WebDriver driver){
        this.driver = driver;
    }
    public void authorization(String login, String password){
        driver.findElement(xpath("//button")).click();
        WebElement form = driver.findElement(xpath("//form[@action='/login/']"));
        form.findElement(xpath(".//input[@name='email']")).sendKeys(login);
        form.findElement(xpath(".//input[@name='password']")).sendKeys(password);
        form.findElement(xpath(".//button[@type='submit']")).click();
    }
}
