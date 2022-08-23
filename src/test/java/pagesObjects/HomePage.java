package pagesObjects;

import org.openqa.selenium.WebDriver;

public class HomePage {
    public WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void open(){
        driver.get("https://otus.ru");
    }
}
