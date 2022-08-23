package testsPreconditionsInit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pagesObjects.*;
import webDriverFactory.WebDriverFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestsPreconditionsInit {

    public static Random random;
    public static WebDriver driver;
    public static Authorization auth;
    public static PageActions act;
    public static HomePage homePage;



    @BeforeEach
    public void webDriverStart(){
        driver = WebDriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        pagesObjectsInit();
    }

    @AfterEach
    public void exit(){
        if(driver != null) driver.quit();
    }

    public void pagesObjectsInit() {
        auth = new Authorization(driver);
        act = new PageActions(driver);
        homePage = new HomePage(driver);
        random = new Random();
    }
}
